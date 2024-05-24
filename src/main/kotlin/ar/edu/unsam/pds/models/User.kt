package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.repository.Element
import java.util.UUID

class User(
    var name: String,
    var lastName: String,
    var email: String,
    var image: String,
) : Element {
    val id: String = UUID.randomUUID().toString()

    val assignmentsList = mutableSetOf<Assignment>()

    override fun findMe(value: String): Boolean = id == value

    fun subscribedCourses(): Set<Course> {
        return assignmentsList.map { it.course }.toSet()
    }

    fun addAssignment(assignment: Assignment){
        assignmentsList.add(assignment)
    }
}