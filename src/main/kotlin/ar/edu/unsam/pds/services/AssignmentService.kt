package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
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
        return assignmentRepository.findById(idAssignment).map {
            return@map Mapper.buildAssignmentDto(it)
        }.orElseThrow {
            NotFoundException("Clase no encontrada")
        }
    }

    fun subscribe(idUser: String, idAssignment: String): SubscribeResponseDto {
        val assignment = assignmentRepository.findById(idAssignment).orElseThrow {
            NotFoundException("Clase no encontrada")
        }
        val user = userRepository.findById(idUser).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }

        assignment.addSubscribedUser(user)
        user.addAssignment(assignment)
        //TODO: Agregar update cuando pongamos persistencia

        return Mapper.subscribeResponse(idUser, idAssignment)
    }
}
