package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.utils.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.test.context.support.WithMockUser

class CoursesServiceTest : BootstrapNBTest() {
    private lateinit var institutionService: InstitutionService
    private lateinit var userService: UserService
    private lateinit var courseServices: CoursesService

    @BeforeEach
    fun setUpCoursesServiceTest() {
        institutionService = InstitutionService(
            institutionRepository = institutionRepository
        )

        userService = UserService(
            userRepository = userRepository,
            principalRepository = principalRepository,
            institutionService = institutionService
        )

        courseServices = CoursesService(
            courseRepository = courseRepository
        )
    }

    @Test
    fun `test get all courses`() {
        val obtainedValue = courseServices.getAll("").toList()
        val expectedValue = courses.map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get classic dance course`() {
        val obtainedValue = courseServices.getAll("classic dance").toList()

        val expectedValue = listOf(courses[0]).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get modern dance course`() {
        val obtainedValue = courseServices.getAll("modern").toList()

        val expectedValue = listOf(courses[1]).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get yoga course`() {
        val obtainedValue = courseServices.getAll("yoga_category").toList()

        val expectedValue = listOf(courses[2]).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular course`() {
        val obtainedValue = courseServices.getCourse(courses[0].id.toString())
        val expectedValue = Mapper.buildCourseDetailDto(courses[0])

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    @WithMockUser(username = "adam@email.com", roles = [])
    fun `test delete a particular course`() {
        assertEquals(
            listOf(courses[0], courses[1], courses[2]),
            courseRepository.findAll()
        )

        courseServices.deleteCourse(courses[2].id.toString())

        assertEquals(
            listOf(courses[0], courses[1]),
            courseRepository.findAll()
        )
    }

    @Test
    @WithMockUser(username = "adam@email.com", roles = [])
    fun `test create a course`() {
        val obtainedValue = courseServices.createCourse(CourseRequestDto(
            title = "new course",
            description = "new course description",
            category = "new category",
            image = ""
        ))

        val expectedValue = CourseResponseDto(
            id = obtainedValue?.id!!,
            title = "new course",
            description = "new course description",
            category = "new category",
            image = ""
        )

        assertEquals(obtainedValue, expectedValue)
    }
}