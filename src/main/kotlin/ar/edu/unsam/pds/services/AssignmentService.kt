package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service

@Service
class AssignmentService(
    private val assignmentRepository: AssignmentRepository
) {

    fun getAll(): List<AssignmentResponseDto> {
        val assignments = assignmentRepository.getAll()
        return assignments.map { Mapper.buildAssignmentDto(it) }
    }

    fun getAssignment(idAssignment: String): AssignmentResponseDto {
        val assignments = assignmentRepository.findById(idAssignment)
        return Mapper.buildAssignmentDto(assignments as Assignment)
    }
}
