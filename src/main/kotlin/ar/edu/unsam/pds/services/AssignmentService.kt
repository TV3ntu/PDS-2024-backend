package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.PermissionDeniedException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.ScheduleRepository
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AssignmentService(
    private val assignmentRepository: AssignmentRepository,
    private val userRepository: UserRepository,
    private val scheduleRepository: ScheduleRepository,
    private val courseRepository: CourseRepository
) {

    fun getAll(): List<AssignmentResponseDto> {
        val assignments = assignmentRepository.findAll()
        return assignments.map { Mapper.buildAssignmentDto(it) }
    }

    fun getAssignment(idAssignment: String): AssignmentResponseDto {
        val assignments = findAssignmentById(idAssignment)
        return Mapper.buildAssignmentDto(assignments)
    }

    @Transactional
    fun subscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = findAssignmentById(idAssignment)
        val user = findUserById(idUser)

        user.addAssignment(assignment)
        assignment.addSubscribedUser(user)

        userRepository.save(user)
        return Mapper.subscribeResponse(idUser, idAssignment)
    }

    @Transactional
    fun unsubscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = findAssignmentById(idAssignment)
        val user = findUserById(idUser)

        user.removeAssignment(assignment)
        assignment.removeSubscribedUser(user)

        userRepository.save(user)
        return Mapper.unsubscribeResponse(idUser, idAssignment)
    }

    private fun findUserById(idUser: String): User {
        val uuid = UUID.fromString(idUser)
        return userRepository.findById(uuid).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }
    }

    private fun findAssignmentById(idAssignment: String): Assignment {
        val uuid = UUID.fromString(idAssignment)
        return assignmentRepository.findById(uuid).orElseThrow {
            NotFoundException("Clase no encontrada")
        }
    }

    fun createAssignment(assignment: AssignmentRequestDto): AssignmentResponseDto {
        val courseId = UUID.fromString(assignment.idCourse)
        val course = courseRepository.findById(courseId).orElseThrow {
            NotFoundException("course no encontrado")
        }


        val newSchedule = Schedule(
            days = assignment.schedule.days,
            startTime = assignment.schedule.startTime,
            endTime = assignment.schedule.endTime,
            startDate = assignment.schedule.startDate,
            endDate = assignment.schedule.endDate,
            recurrenceWeeks = assignment.schedule.recurrenceWeeks,
        )

        scheduleRepository.save(newSchedule)

        val newAssignment = Assignment(
            quotas = assignment.quotas,
            isActive = true,
            price = assignment.price,
            schedule = newSchedule
        )

        assignmentRepository.save(newAssignment)
        course.addAssignment(newAssignment)
        courseRepository.save(course)

        return Mapper.buildAssignmentDto(newAssignment)
    }

    @Transactional
    fun deleteAssignment(idAssignment: String, principal: Principal) {
        val isOwner = assignmentRepository.isOwner(UUID.fromString(idAssignment), principal)
        if (!isOwner) throw PermissionDeniedException("Acceso denegado")

        val assignment = findAssignmentById(idAssignment)

        if (assignment.hasAnySubscribedUser()) {
            throw ValidationException("No se puede eliminar un curso con usuarios inscriptos")
        }

        assignmentRepository.delete(assignment)
    }

}