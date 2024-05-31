package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.util.*

@Entity @Table(name = "APP_ASSIGNMENT")
class Assignment(
    val quotas: Int,
    var isActive: Boolean,
    val price: Double,

    @ManyToOne(fetch = FetchType.EAGER)
    var schedule: Schedule

) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "assignmentsList")
    private val subscribedUsers = mutableSetOf<User>()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    lateinit var course: Course

    fun status(): String {
        return if (schedule.isBeforeEndDate(LocalDate.now())) {
            "Confirmado"
        } else {
            "Finalizado"
        }
    }

    fun quantityAvailable(): Int {
        return quotas - subscribedUsers.size
    }

    fun attachCourse(course: Course) {
        this.course = course
    }

    fun addSubscribedUser(user: User) {
        if (quotas > subscribedUsers.size) {
            subscribedUsers.add(user)
        } else {
            throw ValidationException("No hay cupos disponibles")
        }
    }

    fun removeSubscribedUser(user: User) {
        if (!subscribedUsers.any { it.id == user.id }) {
            throw ValidationException("El usuario no está subscripto")
        } else {
            subscribedUsers.removeIf { it.id == user.id }
        }
    }
}