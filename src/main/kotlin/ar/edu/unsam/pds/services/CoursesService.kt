package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service

@Service
class CoursesService(
    private val courseRepository: CourseRepository
) {

    fun getAll(): List<CourseResponseDto> {
        val courses = courseRepository.getAll()
        return courses.map { Mapper.buildCourseDto(it) }
    }

    fun getCourse(idCourse: String): CourseDetailResponseDto {
        val course = courseRepository.findById(idCourse) as Course
        return Mapper.buildCourseDetailDto(course)
    }



    fun getAssignmentOfCourse(idCourse: String): List<Assignment> {
        val course = courseRepository.findById(idCourse) as Course
        return course.getAssignments().toList()
    }
}