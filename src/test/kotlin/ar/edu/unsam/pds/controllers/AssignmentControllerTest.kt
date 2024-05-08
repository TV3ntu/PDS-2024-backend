package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Assignment
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
        val assignment1 = Assignment(LocalTime.now(), LocalTime.now(), mutableListOf("Monday", "Wednesday"), 10, true, 100)
        val assignments = listOf(assignment1)
        `when`(assignmentService.getAll()).thenReturn(assignments)

        val responseEntity = assignmentController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignments)
    }

    @Test
    fun `test assignmentItem`() {
        val assignment1 = Assignment(LocalTime.now(), LocalTime.now(), mutableListOf("Monday", "Wednesday"), 10, true, 100)
        val idAssignment = "456" // Id de asignación válido
        val assignment = assignment1
        `when`(assignmentService.getAssignment(idAssignment)).thenReturn(assignment)

        val responseEntity = assignmentController.getAssignment(idAssignment)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignment)
    }
}

