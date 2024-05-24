package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.repository.Element
import ar.edu.unsam.pds.exceptions.ValidationException
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

    fun addAssignment(assignment: Assignment) {
        if (assignmentsList.contains(assignment)) {
            throw ValidationException("El usuario ya está subscripto a esta asignación")
        } else {
            assignmentsList.add(assignment)
        }
    }

    fun removeAssignment(assignment: Assignment) {
        if (!assignmentsList.contains(assignment)) {
            throw ValidationException("El usuario no está subscripto a esta asignación")
        } else {
            assignmentsList.remove(assignment)
        }
    }

}