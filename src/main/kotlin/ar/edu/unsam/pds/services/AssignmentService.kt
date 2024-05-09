package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import org.springframework.stereotype.Service

@Service
class AssignmentService {
    private val assignmentRepository = AssignmentRepository

    fun getAll(): List<AssignmentResponseDto> {
        val assignments = assignmentRepository.getAll()
        return assignments.map { buildAssignmentDto(it) }
    }

    fun getAssignment(idAssignment: String): AssignmentResponseDto {
        val assignments = assignmentRepository.findByObjectId(idAssignment)
        return buildAssignmentDto(assignments as Assignment)
    }

    private fun buildAssignmentDto(assignment: Assignment): AssignmentResponseDto {
        return AssignmentResponseDto(
            assignment.id,
            assignment.startTime,
            assignment.endTime,
            assignment.day,
            assignment.quotas,
            assignment.isActive,
            assignment.price
        )
    }
}
