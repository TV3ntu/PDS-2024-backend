package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
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
    fun `test get all courses`() {
        val courses = listOf(
            CourseResponseDto(
                id = "123",
                title = "title 1",
                description = "description",
                category = "category",
                image = ""
            )
        )

        `when`(courseServices.getAll("")).thenReturn(courses)
        val responseEntity = coursesController.getAll(null)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courses)
    }

    @Test
    fun `test get a particular course`() {
        val course = CourseDetailResponseDto(
            id = "123",
            title = "title 1",
            description = "description",
            category = "category",
            image = "",
            assignments = mutableSetOf()
        )
        `when`(courseServices.getCourse("123")).thenReturn(course)

        val responseEntity = coursesController.getCourse("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == course)
    }
}