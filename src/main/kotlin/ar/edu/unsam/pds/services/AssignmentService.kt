package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service

@Service
class AssignmentService(
    private val assignmentRepository: AssignmentRepository,
    private val userRepository: UserRepository
) {

    fun getAll(): List<AssignmentResponseDto> {
        val assignments = assignmentRepository.getAll()
        return assignments.map { Mapper.buildAssignmentDto(it) }
    }

    fun getAssignment(idAssignment: String): AssignmentResponseDto {
        val assignments = assignmentRepository.findById(idAssignment)
        return Mapper.buildAssignmentDto(assignments as Assignment)
    }

    fun subscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = findAssignmentById(idAssignment)
        val user = findUserById(idUser)

        user.addAssignment(assignment)
        assignment.addSubscribedUser(user)
        //TODO: Agregar update cuando pongamos persistencia

        return Mapper.subscribeResponse(idUser, idAssignment)
    }

    private fun findUserById(idUser: String): User {
        return userRepository.findById(idUser).orElseThrow { NotFoundException("Usuario no encontrado") }
    }

    private fun findAssignmentById(idAssigment: String): Assignment {
        return assignmentRepository.findById(idAssigment).orElseThrow { NotFoundException("Clase no encontrada") }
    }
}
