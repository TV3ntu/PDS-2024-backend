package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.CoursesService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

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
    fun `test coursesAll`() {
        val course1 = Course("title 1", "description", "category", "")
        val courses = listOf(course1)
        `when`(courseServices.getCoursesAll()).thenReturn(courses)

        val responseEntity = coursesController.coursesAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courses)
    }

    @Test
    fun `test coursesList`() {
        val course1 = Course("title 1", "description", "category", "")
        val idInstitution = "123" // Valid institution ID
        val courses = listOf(course1)
        `when`(courseServices.getCoursesList(idInstitution)).thenReturn(courses)

        val responseEntity = coursesController.coursesList(idInstitution)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courses)
    }

    @Test
    fun `test courseItem`() {
        val course1 = Course("title 1", "description", "category", "")
        val idCourse = "123" // Valid course ID
        val course = course1
        `when`(courseServices.getCourseItem(idCourse)).thenReturn(course)

        val responseEntity = coursesController.courseItem(idCourse)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == course)
    }
}