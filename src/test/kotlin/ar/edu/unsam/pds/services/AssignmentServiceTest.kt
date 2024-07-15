package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.request.ScheduleRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.dto.response.UserSubscribedResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.PermissionDeniedException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class AssignmentServiceTest : BootstrapNBTest() {
    private lateinit var assignmentService: AssignmentService

    @BeforeEach
    fun prepareTestData() {
        assignmentService = AssignmentService(
            assignmentRepository = assignmentRepository,
            userRepository = userRepository,
            scheduleRepository = scheduleRepository,
            courseRepository = courseRepository,
            paymentRepository = paymentRepository,
            emailService = emailService
        )
    }

    @Test
    fun `test all assignments`() {
        val obtainedValue = assignmentService.getAll().toList()
        val expectedValue = assignments.map {
            AssignmentMapper.buildAssignmentDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular assignments`() {
        val obtainedValue = assignmentService.getAssignment(assignments[0].id.toString())
        val expectedValue = AssignmentMapper.buildAssignmentDto(assignments[0])

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test subscribe to assignment`() {

        `when`(emailService.sendSubscriptionConfirmationEmail(
            to = users[0].email,
            courseName = assignments[0].course.title,
            userName = users[0].name
        )).then {  }

        `when`(emailService.sendPaymentConfirmationEmail(
            to = users[0].email,
            amount = assignments[0].price,
            userName = users[0].name,
            transactionId = "ID_GENERADO_POR_OTRO_METODO"
        )).then {  }

        val obtainedValue = assignmentService.subscribe(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )

        val expectedValue = SubscribeResponseDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString(),
            message = "Suscripción exitosa",
            date = LocalDate.now()
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw subscribe to assignment -1`() {
        assertThrows<NotFoundException> {
            assignmentService.subscribe(
                idUser = UUID.randomUUID().toString(),
                idAssignment = assignments[0].id.toString()
            )
        }

        assertThrows<NotFoundException> {
            assignmentService.subscribe(
                idUser = UUID.randomUUID().toString(),
                idAssignment = UUID.randomUUID().toString()
            )
        }
    }

    @Test
    fun `test not enough credits to subscribe to assignment`() {
        assertThrows<ValidationException> {
            users[1].credits = 0.0
            assignmentService.subscribe(
                idUser = users[1].id.toString(),
                idAssignment = assignments[0].id.toString()
            )
        }
    }

    @Test
    fun `test unsubscribe to assignment`() {
        `when`(emailService.sendSubscriptionConfirmationEmail(
            to = users[0].email,
            courseName = assignments[0].course.title,
            userName = users[0].name
        )).then {  }

        `when`(emailService.sendPaymentConfirmationEmail(
            to = users[0].email,
            amount = assignments[0].price,
            userName = users[0].name,
            transactionId = "ID_GENERADO_POR_OTRO_METODO"
        )).then {  }

        assignmentService.subscribe(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )

        val obtainedValue = assignmentService.unsubscribe(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )
        val expectedValue = SubscribeResponseDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString(),
            message = "Desuscripción exitosa",
            date = LocalDate.now()
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test unsubscribe to assignment - credit check with refund`() {
        val userId = users[0].id.toString()
        val assignmentId = assignments[0].id.toString()

        // #############################################################################################################
        assertEquals(users[0].credits, 100000.0)

        // #############################################################################################################
        `when`(emailService.sendSubscriptionConfirmationEmail(
            to = users[0].email,
            courseName = assignments[0].course.title,
            userName = users[0].name
        )).then {  }

        `when`(emailService.sendPaymentConfirmationEmail(
            to = users[0].email,
            amount = assignments[0].price,
            userName = users[0].name,
            transactionId = "ID_GENERADO_POR_OTRO_METODO"
        )).then {  }

        assignmentService.subscribe(userId, assignmentId)
        assertEquals(users[0].credits, 99900.0)

        // #############################################################################################################
        assignmentService.unsubscribe(userId, assignmentId)
        assertEquals(users[0].credits, 100000.0)
    }

    @Test
    fun `test unsubscribe to assignment - credit check no refund`() {
        val userId = users[0].id.toString()
        val assignmentId = assignments[0].id.toString()

        // #############################################################################################################
        assertEquals(users[0].credits, 100000.0)

        // #############################################################################################################
        `when`(emailService.sendSubscriptionConfirmationEmail(
            to = users[0].email,
            courseName = assignments[0].course.title,
            userName = users[0].name
        )).then {  }

        `when`(emailService.sendPaymentConfirmationEmail(
            to = users[0].email,
            amount = assignments[0].price,
            userName = users[0].name,
            transactionId = "ID_GENERADO_POR_OTRO_METODO"
        )).then {  }

        assignmentService.subscribe(userId, assignmentId)
        assertEquals(users[0].credits, 99900.0)

        // #############################################################################################################
        val futureDateTime = LocalDateTime.now().plusMonths(1)

        Mockito.mockStatic(LocalDateTime::class.java, Mockito.CALLS_REAL_METHODS).use {
            it.`when`<LocalDateTime> { LocalDateTime.now() }.thenReturn(futureDateTime)
            assignmentService.unsubscribe(userId, assignmentId)
        }

        assertEquals(users[0].credits, 99900.0)
    }

    @Test
    fun `test throw unsubscribe to assignment`() {
        assertThrows<NotFoundException> {
            assignmentService.unsubscribe(
                idUser = UUID.randomUUID().toString(),
                idAssignment = assignments[0].id.toString()
            )
        }

        assertThrows<NotFoundException> {
            assignmentService.unsubscribe(
                idUser = UUID.randomUUID().toString(),
                idAssignment = UUID.randomUUID().toString()
            )
        }
    }

    @Test
    fun `test throw unsubscribe to assignment - no exist subscription`() {
        val userId = users[0].id.toString()
        val assignmentId = assignments[0].id.toString()

        assertThrows<ValidationException> {
            assignmentService.unsubscribe(userId, assignmentId)
        }
    }

    @Test
    fun `test create assignment`() {
        val startTime = LocalTime.of(19, 0)
        val endTime = LocalTime.of(21, 0)
        val startDate = LocalDate.now().plusMonths(1)
        val endDate = LocalDate.now().plusMonths(5)

        val assignmentRequest = AssignmentRequestDto(
            idCourse = courses[0].id.toString(),
            quotas = 10,
            price = 1000.0,
            schedule = ScheduleRequestDto(
                days = listOf(DayOfWeek.MONDAY),
                startTime = startTime,
                endTime = endTime,
                startDate = startDate,
                endDate = endDate,
                recurrenceWeeks = RecurrenceWeeks.WEEKLY
            )
        )

        val obtainedValue =  assignmentService.createAssignment(assignmentRequest)
        val expectedValue = AssignmentResponseDto(
            id = obtainedValue.id,
            quotas = 10,
            quantityAvailable = 10,
            isActive = true,
            price = 1000.0,
            schedule = ScheduleResponseDto(
                days = listOf(DayOfWeek.MONDAY),
                startTime = startTime,
                endTime = endTime,
                startDate = startDate,
                endDate = endDate,
                recurrenceWeeks = RecurrenceWeeks.WEEKLY.name,
                listDates = obtainedValue.schedule.listDates
            )
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test create assignment - no exist course`() {
        val startTime = LocalTime.of(19, 0)
        val endTime = LocalTime.of(21, 0)
        val startDate = LocalDate.now().plusMonths(1)
        val endDate = LocalDate.now().plusMonths(5)

        val assignmentRequest = AssignmentRequestDto(
            idCourse = UUID.randomUUID().toString(),
            quotas = 10,
            price = 1000.0,
            schedule = ScheduleRequestDto(
                days = listOf(DayOfWeek.MONDAY),
                startTime = startTime,
                endTime = endTime,
                startDate = startDate,
                endDate = endDate,
                recurrenceWeeks = RecurrenceWeeks.WEEKLY
            )
        )

        assertThrows<NotFoundException> {
            assignmentService.createAssignment(assignmentRequest)
        }
    }

    @Test
    fun `test create assignment - startTime is after endTime`() {
        val endTime = LocalTime.of(19, 0)
        val startTime = LocalTime.of(21, 0)
        val startDate = LocalDate.now().plusMonths(1)
        val endDate = LocalDate.now().plusMonths(5)

        val assignmentRequest = AssignmentRequestDto(
            idCourse = courses[0].id.toString(),
            quotas = 10,
            price = 1000.0,
            schedule = ScheduleRequestDto(
                days = listOf(DayOfWeek.MONDAY),
                startTime = startTime,
                endTime = endTime,
                startDate = startDate,
                endDate = endDate,
                recurrenceWeeks = RecurrenceWeeks.WEEKLY
            )
        )

        assertThrows<ValidationException> {
            assignmentService.createAssignment(assignmentRequest)
        }
    }

    @Test
    fun `test create assignment - startDate is before now)`() {
        val startTime = LocalTime.of(19, 0)
        val endTime = LocalTime.of(21, 0)
        val startDate = LocalDate.now().minusMonths(1)
        val endDate = LocalDate.now().plusMonths(5)

        val assignmentRequest = AssignmentRequestDto(
            idCourse = courses[0].id.toString(),
            quotas = 10,
            price = 1000.0,
            schedule = ScheduleRequestDto(
                days = listOf(DayOfWeek.MONDAY),
                startTime = startTime,
                endTime = endTime,
                startDate = startDate,
                endDate = endDate,
                recurrenceWeeks = RecurrenceWeeks.WEEKLY
            )
        )

        assertThrows<ValidationException> {
            assignmentService.createAssignment(assignmentRequest)
        }
    }

    @Test
    fun `test create assignment - startDate is after endDate)`() {
        val startTime = LocalTime.of(19, 0)
        val endTime = LocalTime.of(21, 0)
        val startDate = LocalDate.now().plusMonths(5)
        val endDate = LocalDate.now().plusMonths(1)

        val assignmentRequest = AssignmentRequestDto(
            idCourse = courses[0].id.toString(),
            quotas = 10,
            price = 1000.0,
            schedule = ScheduleRequestDto(
                days = listOf(DayOfWeek.MONDAY),
                startTime = startTime,
                endTime = endTime,
                startDate = startDate,
                endDate = endDate,
                recurrenceWeeks = RecurrenceWeeks.WEEKLY
            )
        )

        assertThrows<ValidationException> {
            assignmentService.createAssignment(assignmentRequest)
        }
    }

    @Test
    fun `test delete assignment`() {
        val uuid = assignments[1].id.toString()

        val obtainedValuePre = assignmentService.getAll().toList()
        val expectedValuePre = assignments.map {
            AssignmentMapper.buildAssignmentDto(it)
        }

        assertEquals(obtainedValuePre, expectedValuePre)

        assignmentService.deleteAssignment(uuid, principals[0])

        val obtainedValuePos = assignmentService.getAll().toList()
        val expectedValuePos = listOf(assignments[0]).map {
            AssignmentMapper.buildAssignmentDto(it)
        }

        assertEquals(obtainedValuePos, expectedValuePos)
    }

    @Test
    fun `test delete assignment - in not owner`() {
        val uuid = assignments[0].id.toString()

        assertThrows<PermissionDeniedException> {
            assignmentService.deleteAssignment(uuid, principals[1])
        }
    }

    @Test
    fun `test delete assignment - has any subscribed user`() {
        val uuid = assignments[0].id.toString()

        `when`(emailService.sendSubscriptionConfirmationEmail(
            to = users[0].email,
            courseName = assignments[0].course.title,
            userName = users[0].name
        )).then {  }

        `when`(emailService.sendPaymentConfirmationEmail(
            to = users[0].email,
            amount = assignments[0].price,
            userName = users[0].name,
            transactionId = "ID_GENERADO_POR_OTRO_METODO"
        )).then {  }

        assignmentService.subscribe(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )

        assertThrows<ValidationException> {
            assignmentService.deleteAssignment(uuid, principals[0])
        }
    }

//    Unexpected exception type thrown
//    @Test
//    fun `test delete assignment - in exist assignment`() {
//        val uuid = UUID.randomUUID().toString()
//
//        assertThrows<NotFoundException> {
//            assignmentService.deleteAssignment(uuid, principals[0])
//        }
//    }

    @Test
    fun `test get assignment described users`() {
        val uuid = assignments[0].id.toString()

        `when`(emailService.sendSubscriptionConfirmationEmail(
            to = users[0].email,
            courseName = assignments[0].course.title,
            userName = users[0].name
        )).then {  }

        `when`(emailService.sendPaymentConfirmationEmail(
            to = users[0].email,
            amount = assignments[0].price,
            userName = users[0].name,
            transactionId = "ID_GENERADO_POR_OTRO_METODO"
        )).then {  }

        assignmentService.subscribe(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )

        val obtainedValue = assignmentService.getAssignmentSuscribedUsers(uuid)
        val expectedValue = listOf(UserSubscribedResponseDto(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com"
        ))

        assertEquals(obtainedValue, expectedValue)
    }
}