package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.AssigmentResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class AssignmentService {
    private val assignmentRepository = AssignmentRepository
    private val courseRepository = CourseRepository
    fun getAll(): List<AssigmentResponseDto> {
        val assigments = assignmentRepository.getAll()
        return assigments.map { buildAssigmentDto(it) }
    }

    fun getAssignmentList(idCourse: String): List<Assignment> {
        val course = courseRepository.findByObjectId(idCourse) as ar.edu.unsam.pds.models.Course
        return course.getAssignments().toList()

    }

    fun getAssignmentItem(idAssignment: String): Assignment {
        return assignmentRepository.findByObjectId(idAssignment) as Assignment
    }

    private fun buildAssigmentDto(assignment: Assignment): AssigmentResponseDto {
        return AssigmentResponseDto(assignment.id, assignment.startTime, assignment.endTime, assignment.day, assignment.quotas, assignment.isActive, assignment.price)
    }

}