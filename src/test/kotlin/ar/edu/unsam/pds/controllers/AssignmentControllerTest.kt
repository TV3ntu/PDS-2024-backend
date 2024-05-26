package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
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

@ExtendWith(MockitoExtension::class)
class AssignmentControllerTest {
    @Mock
    private lateinit var assignmentService: AssignmentService
    private lateinit var assignmentController: AssignmentController

    @BeforeEach
    fun setUp() {
        assignmentController = AssignmentController()
        assignmentController.assignmentService = assignmentService
    }

    @Test
    fun `test assignmentAll`() {
        val schedule = ScheduleResponseDto(
            days = listOf(DayOfWeek.FRIDAY),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            recurrenceWeeks = 1,
            listDates = listOf(LocalDate.now().toString())
        )
        val assignments = listOf(
            AssignmentResponseDto(
                id = "123",
                quotas = 10,
                quantityAvailable = 100,
                isActive = true,
                price = 100.0,
                scheduleResponseDto = schedule
            )
        )

        `when`(assignmentService.getAll()).thenReturn(assignments)

        val responseEntity = assignmentController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignments)
    }

    @Test
    fun `test assignmentItem`() {
        val schedule = ScheduleResponseDto(
            days = listOf(DayOfWeek.FRIDAY),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            startDate = LocalDate.now(),
            endDate = LocalDate.now(),
            recurrenceWeeks = 1,
            listDates = listOf(LocalDate.now().toString())
        )
        val assignment = AssignmentResponseDto(
            id = "123",
            quotas = 10,
            quantityAvailable = 100,
            isActive = true,
            price = 100.0,
            scheduleResponseDto = schedule
        )
        `when`(assignmentService.getAssignment("123")).thenReturn(assignment)

        val responseEntity = assignmentController.getAssignment("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignment)
    }
}

