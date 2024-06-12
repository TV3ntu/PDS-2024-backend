package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.SubscribeRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
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
import java.time.LocalTime
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class AssignmentControllerTest {
    @Mock
    private lateinit var assignmentService: AssignmentService
    private lateinit var assignmentController: AssignmentController

    private lateinit var assignment: AssignmentResponseDto
    private lateinit var schedule: ScheduleResponseDto
    private lateinit var subscribeRequest: SubscribeRequestDto

    @BeforeEach
    fun setUp() {
        assignmentController = AssignmentController()
        assignmentController.assignmentService = assignmentService

        schedule = ScheduleResponseDto(
            days = listOf(DayOfWeek.FRIDAY),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            recurrenceWeeks = RecurrenceWeeks.WEEKLY.name,
            listDates = listOf(LocalDate.now().toString())
        )

        assignment = AssignmentResponseDto(
            id = UUID.randomUUID().toString(),
            quotas = 10,
            quantityAvailable = 100,
            isActive = true,
            price = 100.0,
            schedule = schedule
        )

        subscribeRequest = SubscribeRequestDto(
            idUser = UUID.randomUUID().toString(),
            idAssignment = assignment.id
        )
    }

    @Test
    fun `test assignmentAll`() {
        val assignments = listOf(assignment)

        `when`(assignmentService.getAll()).thenReturn(assignments)

        val responseEntity = assignmentController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignments)
    }

    @Test
    fun `test assignmentItem`() {
        `when`(assignmentService.getAssignment("123")).thenReturn(assignment)

        val responseEntity = assignmentController.getAssignment("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignment)
    }

    @Test
    fun `test subscribe to assignment`() {
        val response = SubscribeResponseDto(
            idUser = subscribeRequest.idUser,
            idAssignment = subscribeRequest.idAssignment,
            message = "subscribe",
            date = LocalDate.now()
        )

        `when`(
            assignmentService.subscribe(
                idUser = subscribeRequest.idUser,
                idAssignment = subscribeRequest.idAssignment
            )
        ).thenReturn(response)

        val responseEntity = assignmentController.subscribeToAssignment(subscribeRequest)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == response)
    }

    @Test
    fun `test unsubscribe to assignment`() {
        val response = SubscribeResponseDto(
            idUser = subscribeRequest.idUser,
            idAssignment = subscribeRequest.idAssignment,
            message = "unsubscribe",
            date = LocalDate.now()
        )

        `when`(
            assignmentService.unsubscribe(
                idUser = subscribeRequest.idUser,
                idAssignment = subscribeRequest.idAssignment
            )
        ).thenReturn(response)

        val responseEntity = assignmentController.unsubscribeToAssignment(subscribeRequest)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == response)
    }
}

