package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AssignmentService(
    private val assignmentRepository: AssignmentRepository,
    private val userRepository: UserRepository
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

        userRepository.save(user)
        return AssignmentMapper.subscribeResponse(idUser, idAssignment)
    }

    @Transactional
    fun unsubscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = findAssignmentById(idAssignment)
        val user = findUserById(idUser)

        user.removeAssignment(assignment)
        assignment.removeSubscribedUser(user)

        userRepository.save(user)
        return AssignmentMapper.unsubscribeResponse(idUser, idAssignment)
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
}