package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.SubscribeRequestDto
import ar.edu.unsam.pds.dto.request.UnsubscribeRequestDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ActiveProfiles("test")
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
            subscriptionRepository = subscriptionRepository,
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
    fun `test get a particular user`() {
        val obtainedValue = assignmentService.getAssignment(assignments[0].id.toString())
        val expectedValue = AssignmentMapper.buildAssignmentDto(assignments[0])

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test subscribe to assignment`() {
        val subscribeRequestDto = SubscribeRequestDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString(),
            startDate = LocalDateTime.now()
        )
        val obtainedValue = assignmentService.subscribe(subscribeRequestDto)

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
        var subscribeRequestDto = SubscribeRequestDto(
            idUser = UUID.randomUUID().toString(),
            idAssignment = assignments[0].id.toString(),
            startDate = LocalDateTime.now()
        )
        assertThrows<NotFoundException> {
            assignmentService.subscribe(subscribeRequestDto
            )
        }

        subscribeRequestDto = SubscribeRequestDto(
            idUser = users[0].id.toString(),
            idAssignment = UUID.randomUUID().toString(),
            startDate = LocalDateTime.now()
        )
        assertThrows<NotFoundException> {
            assignmentService.subscribe(subscribeRequestDto)
        }
    }

    @Test
    fun `test not enough credits to subscribe to assignment`() {

        val subscribeRequestDto = SubscribeRequestDto(
            idUser = users[1].id.toString(),
            idAssignment = assignments[0].id.toString(),
            startDate = LocalDateTime.now()
        )
        assertThrows<ValidationException> {
            users[1].credits = 0.0
            assignmentService.subscribe(subscribeRequestDto)
        }
    }

    @Test
    fun `test unsubscribe to assignment`() {
        val subscribeRequestDto = SubscribeRequestDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString(),
            startDate = LocalDateTime.now()
        )
        assignmentService.subscribe(subscribeRequestDto)

        val unsubscribeRequestDto = UnsubscribeRequestDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )
        val obtainedValue = assignmentService.unsubscribe(unsubscribeRequestDto)

        val expectedValue = SubscribeResponseDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString(),
            message = "Desuscripción exitosa",
            date = LocalDate.now()
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw unsubscribe to assignment`() {
        var unsubscribeRequestDto = UnsubscribeRequestDto(
            idUser = users[0].id.toString(),
            idAssignment = assignments[0].id.toString()
        )
        assertThrows<NotFoundException> {
            assignmentService.unsubscribe(unsubscribeRequestDto)
        }

        unsubscribeRequestDto = UnsubscribeRequestDto(
            idUser = UUID.randomUUID().toString(),
            idAssignment = UUID.randomUUID().toString()
        )
        assertThrows<NotFoundException> {
            assignmentService.unsubscribe(unsubscribeRequestDto)
        }
    }
}