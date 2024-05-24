package ar.edu.unsam.pds.models
import ar.edu.unsam.pds.exceptions.ValidationException
import java.util.UUID
import ar.edu.unsam.pds.repository.Element
import java.time.LocalTime

class Assignment (
    val startTime: LocalTime,
    val endTime: LocalTime,
    var day: MutableList<String>,
    val quotas: Int,
    var isActive: Boolean,
    val price: Int,
) : Element  {
    val id: String = UUID.randomUUID().toString()
    
    lateinit var course: Course

  
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
}