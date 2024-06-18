package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.CourseStatsResponseDto
import ar.edu.unsam.pds.mappers.CourseMapper
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.CoursesService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.util.*

@ExtendWith(MockitoExtension::class)
class CoursesControllerTest {
    @Mock
    private lateinit var courseServices: CoursesService
    private lateinit var coursesController: CoursesController
    private lateinit var course: Course
    private lateinit var uuid: String

    private lateinit var principal: Principal

    @BeforeEach
    fun setUp() {
        coursesController = CoursesController()
        coursesController.courseServices = courseServices

        course = Course(
            title = "title 1",
            description = "description",
            category = "category",
            image = ""
        ).apply {
            id = UUID.randomUUID()
        }

        uuid = course.id.toString()

        principal = Principal().apply {
            username = "admin@admin.com"
            password = "123"
            user = User(
                name = "Admin",
                lastName = "Admin",
                email = "admin@admin.com",
                image = "",
                isAdmin = true
            )
            this.initProperties()
        }

    }

    @Test
    fun `test get all courses - not query`() {
        val courses = listOf(CourseMapper.buildCourseDto(course))

        `when`(courseServices.getAll("")).thenReturn(courses)
        val responseEntity = coursesController.getAll(null)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courses)
    }

    @Test
    fun `test get all courses - query`() {
        val courses = listOf(CourseMapper.buildCourseDto(course))

        `when`(courseServices.getAll("query")).thenReturn(courses)
        val responseEntity = coursesController.getAll("query")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courses)
    }

    @Test
    fun `test get a particular course`() {
        val course = CourseMapper.buildCourseDetailDto(course)

        `when`(courseServices.getCourse(uuid)).thenReturn(course)

        val responseEntity = coursesController.getCourse(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == course)
    }

    @Test
    fun `test delete a particular course`() {
        `when`(courseServices.deleteCourse(uuid, principal)).then { }

        val responseEntity = coursesController.deleteCourse(uuid, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
    }

    @Test
    fun `test create a course`() {
        val course = CourseMapper.buildCourseDto(course)

        val courseRequest = CourseRequestDto(
            title = "title 1",
            description = "description",
            category = "category",
            image = "",
            institutionId = "123"
        )

        `when`(courseServices.createCourse(courseRequest)).thenReturn(course)

        val responseEntity = coursesController.createCourse(courseRequest)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == course)
    }

    @Test
    fun `test get course stats`() {
        val courseStats = CourseStatsResponseDto(
            id = uuid,
            title = course.title,
            description = course.description,
            category = course.category,
            image = "",
            totalAssignments = 0,
            totalSubscriptions = 0,
            totalIncome = 0.0,
            mostPopularAssignment = null,
            mostProfitableAssignment = null,
            assignments = mutableSetOf()
        )

        `when`(courseServices.getCourseStats(uuid)).thenReturn(courseStats)

        val responseEntity = coursesController.getCourseStats(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == courseStats)
    }
}