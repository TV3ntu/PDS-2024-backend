package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.request.ScheduleRequestDto
import ar.edu.unsam.pds.dto.request.SubscribeRequestDto
import ar.edu.unsam.pds.dto.request.UnsubscribeRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.AssignmentService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class AssignmentControllerTest {
    @Mock
    private lateinit var assignmentService: AssignmentService
    private lateinit var assignmentController: AssignmentController

    private lateinit var assignmentReq: AssignmentRequestDto
    private lateinit var assignmentRes: AssignmentResponseDto
    private lateinit var scheduleReq: ScheduleRequestDto
    private lateinit var scheduleRes: ScheduleResponseDto
    private lateinit var subscribeRequest: SubscribeRequestDto
    private lateinit var unsubscribeRequestDto: UnsubscribeRequestDto
    private lateinit var principal: Principal
    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        assignmentController = AssignmentController()
        assignmentController.assignmentService = assignmentService

        scheduleReq = ScheduleRequestDto(
            days = listOf(DayOfWeek.FRIDAY),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            recurrenceWeeks = RecurrenceWeeks.WEEKLY,
        )

        scheduleRes = ScheduleResponseDto(
            days = listOf(DayOfWeek.FRIDAY),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            recurrenceWeeks = RecurrenceWeeks.WEEKLY.name,
            listDates = listOf(LocalDate.now().toString())
        )

        assignmentReq = AssignmentRequestDto(
            idCourse = UUID.randomUUID().toString(),
            quotas = 10,
            price = 100.0,
            schedule = scheduleReq
        )

        assignmentRes = AssignmentResponseDto(
            id = UUID.randomUUID().toString(),
            quotas = 10,
            quantityAvailable = 100,
            isActive = true,
            price = 100.0,
            schedule = scheduleRes
        )

        subscribeRequest = SubscribeRequestDto(
            idUser = UUID.randomUUID().toString(),
            idAssignment = assignmentRes.id,
            startDate = LocalDateTime.now()
        )

        unsubscribeRequestDto = UnsubscribeRequestDto(
            idUser = UUID.randomUUID().toString(),
            idAssignment = assignmentRes.id
        )

        user = User(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = "",
            credits = 100000.0,
            isAdmin = true
        )

        principal = Principal().apply {
            id = UUID.randomUUID()
            username = this@AssignmentControllerTest.user.email
            password = "123"
            user = this@AssignmentControllerTest.user
            this.initProperties()
        }
    }

    @Test
    fun `test assignmentAll`() {
        val assignments = listOf(assignmentRes)

        `when`(assignmentService.getAll()).thenReturn(assignments)

        val responseEntity = assignmentController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignments)
    }

    @Test
    fun `test assignmentItem`() {
        val uuid = UUID.randomUUID().toString()
        `when`(assignmentService.getAssignment(uuid)).thenReturn(assignmentRes)

        val responseEntity = assignmentController.getAssignment(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignmentRes)
    }

    @Test
    fun `test subscribe to assignment`() {
        val response = SubscribeResponseDto(
            idUser = subscribeRequest.idUser!!,
            idAssignment = subscribeRequest.idAssignment!!,
            message = "subscribe",
            date = LocalDate.now()
        )


        `when`(
            assignmentService.subscribe(subscribeRequest)
        ).thenReturn(response)

        val responseEntity = assignmentController.subscribeToAssignment(subscribeRequest)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == response)
    }

    @Test
    fun `test unsubscribe to assignment`() {
        val response = SubscribeResponseDto(
            idUser = subscribeRequest.idUser!!,
            idAssignment = subscribeRequest.idAssignment!!,
            message = "unsubscribe",
            date = LocalDate.now()
        )

        `when`(
            assignmentService.unsubscribe(subscribeRequest)
        ).thenReturn(response)

        val responseEntity = assignmentController.unsubscribeToAssignment(subscribeRequest)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == response)
    }

    @Test
    fun `test create a particular assignment`() {
        `when`(assignmentService.createAssignment(assignmentReq)).thenReturn(assignmentRes)

        val responseEntity = assignmentController.createAssignment(assignmentReq)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignmentRes)
    }

    @Test
    fun `test delete a particular assignment`() {
        val uuid = UUID.randomUUID().toString()
        `when`(assignmentService.deleteAssignment(uuid, principal)).then { }

        val responseEntity = assignmentController.deleteAssignment(uuid, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == mapOf("message" to "Assignment eliminado correctamente."))
    }
}

