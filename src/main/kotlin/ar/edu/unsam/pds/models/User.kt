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
    private val subscribedAssignments = mutableSetOf<Assignment>()

    override fun findMe(value: String): Boolean = id == value

    fun subscribe(assignment: Assignment) {
        subscribedAssignments.add(assignment)
    }
    fun subscribedCourses(): Set<Course> {
        return subscribedAssignments.map { it.course }.toSet()
    }
}