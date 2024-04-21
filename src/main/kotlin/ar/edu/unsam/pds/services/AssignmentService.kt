package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class AssignmentService {
    private val assignments = AssignmentRepository
    private val courses = CourseRepository
    fun getAssignmentAll(): List<Assignment> {
        return assignments.getAll().toList()

    }

    fun getAssignmentList(idCourse: String): List<Assignment> {
        val course = courses.findByObjectId(idCourse) as ar.edu.unsam.pds.models.Course
        return course.getAssignments().toList()

    }

    fun getAssignmentItem(idAssignment: String): Assignment {
        return assignments.findByObjectId(idAssignment) as Assignment

    }
}