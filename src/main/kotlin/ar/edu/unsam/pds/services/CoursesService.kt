package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.CourseStatsResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.PermissionDeniedException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.CourseMapper
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.security.models.Principal
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class CoursesService(
    private val courseRepository: CourseRepository,
    private val institutionRepository: InstitutionRepository
) {

    fun getAll(query: String): List<CourseResponseDto> {
        val courses = courseRepository.getAllBy(query)
        return courses.map { CourseMapper.buildCourseDto(it) }
    }

    fun getCourse(idCourse: String): CourseDetailResponseDto {
        val course = findCourseById(idCourse)
        return CourseMapper.buildCourseDetailDto(course)
    }

    @Transactional
    fun deleteCourse(idCourse: String, principal: Principal) {
       val isOwner = courseRepository.isOwner(UUID.fromString(idCourse), principal)

        val course = findCourseById(idCourse)

        if (!isOwner) throw PermissionDeniedException("Acceso denegado")

        if (course.assignments.any { it.hasAnySubscribedUser() }) {
            throw ValidationException("No se puede eliminar un curso con usuarios inscriptos")
        }
        courseRepository.delete(course)
    }

    @Transactional
    fun deleteAllById(courseIds: List<String>, principal: Principal) {
        courseIds.forEach { id ->
            deleteCourse(id, principal)
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
        val insitutionId = UUID.fromString(course.institutionId)
        val institution = institutionRepository.findById(insitutionId).orElseThrow {
            NotFoundException("Institución no encontrada")
        }


        val newCourse = Course(
            course.title,
            course.description,
            course.category,
            course.image,
        )
        courseRepository.save(newCourse)

        institution.addCourse(newCourse)
        institutionRepository.save(institution)

        return CourseMapper.buildCourseDto(newCourse)
    }

    fun getCourseStats(idCourse: String): CourseStatsResponseDto? {
        val course = findCourseById(idCourse)
        return CourseMapper.buildCourseStatsDto(course)

    }
}