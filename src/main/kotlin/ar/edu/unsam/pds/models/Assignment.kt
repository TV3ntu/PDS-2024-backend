package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Assignment(
    val quotas: Int,
    var isActive: Boolean,
    val price: Double,
    var schedule: Schedule
) : Element  {
    val id: String = UUID.randomUUID().toString()

    override fun findMe(value: String): Boolean = id == value
}