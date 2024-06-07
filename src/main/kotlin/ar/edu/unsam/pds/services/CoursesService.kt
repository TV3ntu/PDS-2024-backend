package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.CourseStatsResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.utils.Mapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class CoursesService(
    private val courseRepository: CourseRepository,
    private val userService: UserService
    ) {

    fun getAll(query: String): List<CourseResponseDto> {
        val courses = courseRepository.getAllBy(query)
        return courses.map { Mapper.buildCourseDto(it) }
    }

    fun getCourse(idCourse: String): CourseDetailResponseDto {
        val course = findCourseById(idCourse)
        return Mapper.buildCourseDetailDto(course)
    }

    @Transactional
    fun deleteCourse(idCourse: String) {
        val course = findCourseById(idCourse)
        checkAdminPermissions()

        if (course.assignments.any { it.hasAnySubscribedUser() }) {
            throw ValidationException("No se puede eliminar un curso con usuarios inscriptos")
        }
        courseRepository.delete(course)
    }

    @Transactional
    fun deleteAllById(courseIds: List<String>) {
        checkAdminPermissions()

        courseIds.forEach { id ->
            val course = findCourseById(id)
            courseRepository.delete(course)
        }
    }

    private fun findCourseById(idCourse: String): Course {
        val uuid = UUID.fromString(idCourse)
        return courseRepository.findById(uuid).orElseThrow {
            NotFoundException("Curso no encontrado")
        }
    }

    @Transactional
    fun createCourse(course: CourseRequestDto): CourseResponseDto? {
        checkAdminPermissions()

        val newCourse = Course(
            course.title,
            course.description,
            course.category,
            course.image
        )
        courseRepository.save(newCourse)
        return Mapper.buildCourseDto(newCourse)
    }

    fun getCourseStats(idCourse: String): CourseStatsResponseDto? {
        val course = findCourseById(idCourse)
        return Mapper.buildCourseStatsDto(course)

    }


    private fun checkAdminPermissions() {
        try {
            val currentUser = userService.getCurrentUser()
            if (!currentUser.isAdmin) {
                throw NotFoundException("No tienes permiso para realizar esta acción.")
            }
        } catch (e: NotFoundException) {
            throw NotFoundException("Usuario no encontrado. No tienes permiso para realizar esta acción.")
        } catch (e: Exception) {
            throw InternalServerError("Error al verificar los permisos. Inténtelo de nuevo más tarde.")
        }
    }


}