package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.CourseStatsResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.utils.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class CoursesServiceTest : BootstrapNBTest() {
    private lateinit var courseServices: CoursesService
    private lateinit var assignmentService: AssignmentService

    @BeforeEach
    fun setUpCoursesServiceTest() {
        courseServices = CoursesService(
            courseRepository = courseRepository,
            institutionRepository = institutionRepository
        )

        assignmentService = AssignmentService(
            assignmentRepository = assignmentRepository,
            userRepository = userRepository
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
        val uuid = courses[0].id.toString()
        val obtainedValue = courseServices.getCourse(uuid)
        val expectedValue = Mapper.buildCourseDetailDto(courses[0])

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw get a particular course`() {
        val uuid = "029ce681-9f90-45e7-af7f-e74a8cfb4b57"
        assertThrows<NotFoundException> {
            courseServices.getCourse(uuid)
        }
    }

    @Test
    fun `test delete a particular course`() {
        val uuid = courses[2].id.toString()

        val obtainedValuePre = courseServices.getAll("").toList()
        val expectedValuePre = courses.map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValuePre, expectedValuePre)

        courseServices.deleteCourse(uuid)

        val obtainedValuePos = courseServices.getAll("").toList()
        val expectedValuePos = courses.subList(0, 2).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValuePos, expectedValuePos)
    }

    @Test
    fun `test throw delete a particular course`() {
        val userId = users[0].id.toString()
        val courseId = courses[0].id.toString()
        val assignmentId = assignments[0].id.toString()

        assignmentService.subscribe(userId, assignmentId)

        assertThrows<ValidationException> {
            courseServices.deleteCourse(courseId)
        }
    }

    @Test
    fun `test delete a list course`() {
        val uuid = courses[2].id.toString()

        val obtainedValuePre = courseServices.getAll("").toList()
        val expectedValuePre = courses.map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValuePre, expectedValuePre)

        courseServices.deleteAllById(listOf(uuid))

        val obtainedValuePos = courseServices.getAll("").toList()
        val expectedValuePos = courses.subList(0, 2).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValuePos, expectedValuePos)
    }

    @Test
    fun `test create a course`() {
        val courseRequest = CourseRequestDto(
            title = "new course",
            description = "new course description",
            category = "new category",
            image = "",
            institutionId = institutions[0].id.toString()
        )

        val obtainedValue = courseServices.createCourse(courseRequest)
        val id = UUID.fromString(obtainedValue?.id!!)
        val expectedValue = Mapper.buildCourseDto(courseRepository.findById(id).get())

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw create a course`() {
        val courseRequest = CourseRequestDto(
            title = "new course",
            description = "new course description",
            category = "new category",
            image = "",
            institutionId = "029ce681-9f90-45e7-af7f-e74a8cfb4b57"
        )

        assertThrows<NotFoundException> {
            courseServices.createCourse(courseRequest)
        }
    }

    @Test
    fun `test course stats - void`() {
        val uuid = courses[2].id.toString()

        val expectedValue = CourseStatsResponseDto(
            id = uuid,
            title = courses[2].title,
            description = courses[2].description,
            category = courses[2].category,
            image = "",
            totalAssignments = 0,
            totalSubscriptions = 0,
            totalIncome = 0.0,
            mostPopularAssignment = null,
            mostProfitableAssignment = null,
            assignments = mutableSetOf()
        )

        val obtainedValue = courseServices.getCourseStats(uuid)

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test course stats`() {
        val uuid = courses[1].id.toString()

        val expectedValue = CourseStatsResponseDto(
            id = uuid,
            title = courses[1].title,
            description = courses[1].description,
            category = courses[1].category,
            image = "",
            totalAssignments = 1,
            totalSubscriptions = 0,
            totalIncome = 0.0,
            mostPopularAssignment = Mapper.buildAssignmentStatsDto(assignments[1]),
            mostProfitableAssignment = Mapper.buildAssignmentStatsDto(assignments[1]),
            assignments = mutableSetOf(Mapper.buildAssignmentStatsDto(assignments[1]))
        )

        val obtainedValue = courseServices.getCourseStats(uuid)

        assertEquals(obtainedValue, expectedValue)
    }
}