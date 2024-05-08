package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.CoursesService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.time.LocalTime

@ExtendWith(MockitoExtension::class)
class CoursesControllerTest {
    @Mock
    private lateinit var courseServices: CoursesService
    private lateinit var coursesController: CoursesController

    @BeforeEach
    fun setUp() {
        coursesController = CoursesController()
        coursesController.courseServices = courseServices
    }

    @Test
    fun `test get all courses`() {
        val course1 = Course("title 1", "description", "category", "")
        val courses = listOf(course1)
        `when`(courseServices.getAll()).thenReturn(courses)

        val responseEntity = coursesController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courses)
    }

    @Test
    fun `test get a particular course`() {
        val course1 = Course("title 1", "description", "category", "")
        val idCourse = "123" // Valid course ID
        val course = course1
        `when`(courseServices.getCourse(idCourse)).thenReturn(course)

        val responseEntity = coursesController.getCourse(idCourse)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == course)
    }

    @Test
    fun `test get all the assignments from an course`() {
        val assignment = Assignment(LocalTime.now(), LocalTime.now(), mutableListOf("Monday", "Wednesday"), 10, true, 100)
        val assignments = listOf(assignment)
        `when`(courseServices.getAssignmentOfCourse("123")).thenReturn(assignments)

        val responseEntity = coursesController.getAssignmentOfCourse("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == assignments)
    }
}