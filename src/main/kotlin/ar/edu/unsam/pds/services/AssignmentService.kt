package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.dto.response.UserSubscribedResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.PermissionDeniedException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Payment
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.PaymentRepository
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.ScheduleRepository
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class AssignmentService(
    private val assignmentRepository: AssignmentRepository,
    private val userRepository: UserRepository,
    private val paymentRepository: PaymentRepository,
    private val scheduleRepository: ScheduleRepository,
    private val courseRepository: CourseRepository,
    private val emailService: EmailService,
) {

    fun getAll(): List<AssignmentResponseDto> {
        val assignments = assignmentRepository.findAll()
        return assignments.map { AssignmentMapper.buildAssignmentDto(it) }
    }

    fun getAssignment(idAssignment: String): AssignmentResponseDto {
        val assignments = findAssignmentById(idAssignment)
        return AssignmentMapper.buildAssignmentDto(assignments)
    }

    @Transactional
    fun subscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = findAssignmentById(idAssignment)
        val user = findUserById(idUser)

        user.subscribe(assignment)
        assignment.addSubscribedUser(user)
        val payment = Payment(
            amount = assignment.price,
            date = LocalDateTime.now(),
            status = "APPROVED",
            paymentMethod = "CREDITS",
            user = user,
            assignment = assignment
        )

        userRepository.save(user)
        emailService.sendSubscriptionConfirmationEmail(user.email, assignment.course.title, user.name)
        paymentRepository.save(payment)
        emailService.sendPaymentConfirmationEmail(user.email, payment.amount, user.name, payment.id.toString())

        return AssignmentMapper.subscribeResponse(idUser, idAssignment)
    }

    @Transactional
    fun unsubscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = findAssignmentById(idAssignment)
        val user = findUserById(idUser)

        paymentRepository.findLastPaymentByUserIdAndAssignmentId(user.id, assignment.id)?.let {
            if (lessThanTwoHours(it.date)) user.credits += it.amount
        }

        user.removeAssignment(assignment)
        assignment.removeSubscribedUser(user)
        userRepository.save(user)

        return AssignmentMapper.unsubscribeResponse(idUser, idAssignment)
    }

    fun lessThanTwoHours(timeToCompare: LocalDateTime): Boolean {
        val now = LocalDateTime.now()
        val hoursDifference = Duration.between(timeToCompare, now).toHours()
        return hoursDifference < 2
    }

    private fun findUserById(idUser: String): User {
        val uuid = UUID.fromString(idUser)
        return userRepository.findById(uuid).orElseThrow {
            NotFoundException("Usuario no encontrado, para el uuid suministrado")
        }
    }

    private fun findAssignmentById(idAssignment: String): Assignment {
        val uuid = UUID.fromString(idAssignment)
        return assignmentRepository.findById(uuid).orElseThrow {
            NotFoundException("Clase no encontrada, para el uuid suministrado")
        }
    }

    fun createAssignment(assignment: AssignmentRequestDto): AssignmentResponseDto {
        val courseId = UUID.fromString(assignment.idCourse)
        val course = courseRepository.findById(courseId).orElseThrow {
            NotFoundException("Curso no encontrado, para el uuid suministrado")
        }

        if(assignment.schedule.startTime.isAfter(assignment.schedule.endTime)) {
            throw ValidationException("La hora de inicio no puede ser posterior a la hora de fin")
        }

        if(assignment.schedule.startDate.isBefore(LocalDate.now())) {
            throw ValidationException("La fecha de inicio no puede ser anterior a la fecha actual")
        }

        if (assignment.schedule.startDate.isAfter(assignment.schedule.endDate)) {
            throw ValidationException("La fecha de inicio no puede ser posterior a la fecha de fin")
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

        return AssignmentMapper.buildAssignmentDto(newAssignment)
    }

    @Transactional
    fun deleteAssignment(idAssignment: String, principal: Principal) {
        val uuid = UUID.fromString(idAssignment)
        if (!assignmentRepository.isOwner(uuid, principal)) {
            throw PermissionDeniedException("Acceso denegado")
        }

        val assignment = findAssignmentById(idAssignment)

        if (assignment.hasAnySubscribedUser()) {
            throw ValidationException("No se puede eliminar un curso con usuarios inscriptos")
        }

        val course = courseRepository.findByAssignmentId(assignment.id).orElseThrow {
            NotFoundException("curso no encontrado")
        }

        course.removeAssignment(assignment)
        courseRepository.save(course)
    }

    fun getAssignmentSuscribedUsers(idAssignment: String): List<UserSubscribedResponseDto> {
        val assignment = findAssignmentById(idAssignment)
        val users = assignment.subscribedUsers.map { user ->
            UserMapper.buildUserSuscribedDto(user)
        }.toMutableList()
        return users
    }

}