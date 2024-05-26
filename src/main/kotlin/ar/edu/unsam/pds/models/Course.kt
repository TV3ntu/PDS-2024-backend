package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Course (
    val title: String,
    val description: String,
    var category: String,
    var image: String
) : Element {
    val id: String = UUID.randomUUID().toString()
    val assignments = mutableSetOf<Assignment>()

    fun addAssignment(assignment: Assignment) {
        assignments.add(assignment)
        assignment.attachCourse(this)
    }

    override fun findMe(value: String): Boolean = id == value
}