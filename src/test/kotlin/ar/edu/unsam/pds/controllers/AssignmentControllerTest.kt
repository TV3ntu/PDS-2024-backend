package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.services.AssignmentService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
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
        val assignments = listOf(
            AssignmentResponseDto(
                id = "123",
                startTime = LocalTime.now(),
                endTime = LocalTime.now(),
                day = mutableListOf("Monday", "Wednesday"),
                quotas = 10,
                isActive = true,
                price = 100,
                quantityAvailable = 100
            )
        )

        `when`(assignmentService.getAll()).thenReturn(assignments)

        val responseEntity = assignmentController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignments)
    }

    @Test
    fun `test assignmentItem`() {
        val assignment = AssignmentResponseDto(
            id = "123",
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            day = mutableListOf("Monday", "Wednesday"),
            quotas = 10,
            isActive = true,
            price = 100,
            quantityAvailable = 100

        )
        `when`(assignmentService.getAssignment("123")).thenReturn(assignment)

        val responseEntity = assignmentController.getAssignment("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignment)
    }
}

