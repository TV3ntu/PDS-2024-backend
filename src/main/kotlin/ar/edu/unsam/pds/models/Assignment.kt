package ar.edu.unsam.pds.models
import java.time.LocalDateTime
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Assignment (
    val startTime: String,
    val EndTime: String,
    var day: LocalDateTime,
    val quotas: Int,
    var isActive: Boolean,
    val price: Float
) : Element  {
    val id: String = UUID.randomUUID().toString()
}