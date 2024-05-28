package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.utils.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CoursesServiceTest {
    @Autowired private lateinit var courseRepository: CourseRepository
    private lateinit var courseServices: CoursesService

    private lateinit var classicDance: Course
    private lateinit var modernDance: Course
    private lateinit var yoga: Course

    @BeforeEach
    fun setUp() {
        courseServices = CoursesService(courseRepository)

        classicDance = Course(
            title = "classic dance",
            description = "classical dance course",
            category = "dance",
            image = ""
        )

        modernDance = Course(
            title = "modern dance",
            description = "modern dance course",
            category = "dance",
            image = ""
        )

        yoga = Course(
            title = "yoga",
            description = "yoga course",
            category = "yoga_category",
            image = ""
        )

        classicDance = courseRepository.save(classicDance)
        modernDance = courseRepository.save(modernDance)
        yoga = courseRepository.save(yoga)
    }

    @Test
    fun `test get all courses`() {
        val obtainedValue = courseServices.getAll("").toList()
        val expectedValue = listOf(classicDance, modernDance, yoga).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get classic dance course`() {
        val obtainedValue = courseServices.getAll("classic dance").toList()

        val expectedValue = listOf(classicDance).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get modern dance course`() {
        val obtainedValue = courseServices.getAll("modern").toList()

        val expectedValue = listOf(modernDance).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get yoga course`() {
        val obtainedValue = courseServices.getAll("yoga_category").toList()

        val expectedValue = listOf(yoga).map {
            Mapper.buildCourseDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular course`() {
        val obtainedValue = courseServices.getCourse(classicDance.id.toString())
        val expectedValue = Mapper.buildCourseDetailDto(classicDance)

        assertEquals(obtainedValue, expectedValue)
    }
}