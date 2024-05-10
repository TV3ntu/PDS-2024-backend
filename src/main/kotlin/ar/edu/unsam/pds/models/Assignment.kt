package ar.edu.unsam.pds.models
import java.time.LocalDateTime
import java.util.UUID
import ar.edu.unsam.pds.repository.Element
import java.time.LocalTime

class Assignment (
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val quotas: Int,
    var isActive: Boolean,
    val price: Int
) : Element  {
    val id: String = UUID.randomUUID().toString()

    override fun findMe(value: String): Boolean = id == value

    fun dayOfWeek():String{
        return startTime.dayOfWeek.toString()
    }
}