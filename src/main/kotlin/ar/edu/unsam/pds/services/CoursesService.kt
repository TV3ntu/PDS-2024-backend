package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CoursesService {
    private var courseRepository = CourseRepository

    fun getAll(): List<CourseResponseDto> {
        val courses = courseRepository.getAll()
        return courses.map { buildCourseDto(it) }
    }

    fun getCourse(idCourse: String): CourseResponseDto {
        val course = courseRepository.findByObjectId(idCourse) as Course
        return buildCourseDto(course)
    }

    fun getAssignmentOfCourse(idCourse: String): List<Assignment> {
        val course = courseRepository.findByObjectId(idCourse) as Course
        return course.getAssignments().toList()
    }

    private fun buildCourseDto(course: Course): CourseResponseDto {
        return CourseResponseDto(
            course.id,
            course.title,
            course.description,
            course.category,
            course.image
        )
    }
}