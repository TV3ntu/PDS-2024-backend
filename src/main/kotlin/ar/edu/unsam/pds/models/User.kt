package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity @Table(name = "APP_USER")
class User(
    var name: String,
    var lastName: String,
    var email: String,
    var image: String,
) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    var isAdmin: Boolean = false

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_assignment",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "assignment_id")]
    )
    private val assignmentsList = mutableSetOf<Assignment>()

    fun subscribedCourses(): Set<Course> {
        return assignmentsList.map { it.course }.toSet()
    }

    fun addAssignment(assignment: Assignment) {
        if (assignmentsList.any {it.id == assignment.id}) {
            throw ValidationException("El usuario ya está subscripto a esta asignación")
        } else {
            assignmentsList.add(assignment)
        }
    }

    fun removeAssignment(assignment: Assignment) {
        if (!assignmentsList.any {it.id == assignment.id}) {
            throw ValidationException("El usuario no está subscripto a esta asignación")
        } else {
            assignmentsList.remove(assignment)
        }
    }
}