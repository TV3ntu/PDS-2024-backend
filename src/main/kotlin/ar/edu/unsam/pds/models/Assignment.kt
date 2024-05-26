package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Assignment(
    val quotas: Int,
    var isActive: Boolean,
    val price: Double,
    var schedule: Schedule

) : Element  {
    val id: String = UUID.randomUUID().toString()

    lateinit var course: Course

    fun quantityAvailable() = quotas - subscribedUsers.size

    override fun findMe(value: String): Boolean = id == value

    fun attachCourse(course: Course) {
        this.course = course
    }

    val subscribedUsers = mutableSetOf<User>()


    fun addSubscribedUser(user: User){
        if(quotas > subscribedUsers.size){
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
}