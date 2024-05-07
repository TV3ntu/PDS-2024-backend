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
    private val assignments = mutableSetOf<Assignment>()

    fun getAssignments(): Set<Assignment> {
        return assignments
    }

    fun addAssignment(assignment: Assignment) {
        assignments.add(assignment)
    }

    override fun findMe(value: String): Boolean = id == value
}