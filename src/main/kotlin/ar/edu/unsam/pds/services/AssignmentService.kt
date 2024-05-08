package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import org.springframework.stereotype.Service

@Service
class AssignmentService {
    private val assignments = AssignmentRepository

    fun getAll(): List<Assignment> {
        return assignments.getAll().toList()
    }

    fun getAssignment(idAssignment: String): Assignment {
        return assignments.findByObjectId(idAssignment) as Assignment
    }
}