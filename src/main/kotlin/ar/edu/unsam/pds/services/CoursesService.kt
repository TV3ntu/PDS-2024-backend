package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Service

@Service
class CoursesService {
    private var institutionRepository = InstitutionRepository
    private var courseRepository = CourseRepository

    fun getAll(): List<CourseResponseDto> {
        val courses = courseRepository.getAll()

        return courses.map { buildCourseDto(it) }
    }

    fun getCoursesList(idInstitution: String): List<Course> {
        val institution = institutionRepository.findByObjectId(idInstitution) as Institution
        return institution.getCourses().toList()
    }

    fun getCourseItem(idCourse: String): CourseResponseDto {
        val course = courseRepository.findByObjectId(idCourse) as Course
        return buildCourseDto(course)
    }

    private fun buildCourseDto(course: Course): CourseResponseDto {
        return CourseResponseDto(course.id, course.title, course.description, course.category, course.image)
    }

}