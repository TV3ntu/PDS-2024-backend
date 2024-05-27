package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import jakarta.persistence.*
import java.util.UUID

@Entity
class User(
    @Column(length = 30)
    var name: String,
    @Column
    var lastName: String,
    @Column
    var email: String,
    @Column
    var image: String,
)  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id : UUID

    @OneToMany(fetch= FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name="id_user", referencedColumnName = "id")
    val assignmentsList = mutableSetOf<Assignment>()

    fun findMe(value: UUID): Boolean = id.equals(value)


    fun subscribedCourses(): Set<Course> {
        return assignmentsList.map { it.course }.toSet()
    }

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