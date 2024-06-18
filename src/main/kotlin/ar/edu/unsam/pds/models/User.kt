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
    var image: String = "",
    var isAdmin: Boolean = false,
    var credits: Double = 0.0
) : Timestamp(), Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "app_user_assignment",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "assignment_id")]
    )
    val assignmentsList = mutableSetOf<Assignment>()

    fun subscribedCourses(): Set<Course> {
        return assignmentsList.map { it.course }.toSet()
    }

    fun addAssignment(assignment: Assignment) {
        if (assignmentsList.any { it.id == assignment.id }) {
            throw ValidationException("El usuario ya está subscripto a esta asignación")
        } else {
            assignmentsList.add(assignment)
        }
    }

    fun removeAssignment(assignment: Assignment) {
        if (!assignmentsList.any { it.id == assignment.id }) {
            throw ValidationException("El usuario no está subscripto a esta asignación")
        } else {
            assignmentsList.removeIf { it.id == assignment.id }
        }
    }

    fun hasEnoughCredits(credits: Double): Boolean {
        return this.credits >= credits
    }

    fun payCredits(credits: Double) {
        if (hasEnoughCredits(credits)) {
            this.credits -= credits
        } else {
            throw ValidationException("El usuario no tiene suficientes créditos")
        }
    }

    fun subscribe(assignment: Assignment) {
        if (hasEnoughCredits(assignment.price)) {
            payCredits(assignment.price)
        } else {
            throw ValidationException("El usuario no tiene suficientes créditos")
        }
        addAssignment(assignment)
    }
}