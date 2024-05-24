package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service

@Service
class CoursesService(
    private val courseRepository: CourseRepository
) {
    fun getAll(query: String): List<CourseResponseDto> {
        val courses = courseRepository.getAllBy(query)
        return courses.map { Mapper.buildCourseDto(it) }
    }

    fun getCourse(idCourse: String): CourseDetailResponseDto {
        val course = courseRepository.findById(idCourse).orElseThrow { NotFoundException("Curso no encontrado") }
        return Mapper.buildCourseDetailDto(course)
    }
}