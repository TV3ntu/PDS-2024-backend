package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import java.util.UUID
import ar.edu.unsam.pds.repository.Element
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalTime

class Assignment(
    val startTime: LocalTime,
    val endTime: LocalTime,
    var day: MutableList<String>,
    var quotas: Int,
    var isActive: Boolean,
    val price: Int,
) : Element {
    val id: String = UUID.randomUUID().toString()
    private val subscribedUsers = mutableSetOf<User>()

    @JsonIgnore
    lateinit var course: Course

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
        if (!subscribedUsers.contains(user)) {
            throw ValidationException("El usuario no está subscripto")
        } else {
            subscribedUsers.remove(user)
        }
    }

    override fun findMe(value: String): Boolean = id == value
}