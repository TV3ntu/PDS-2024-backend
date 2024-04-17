package ar.edu.unsam.pds.models
import java.time.LocalDateTime
import java.util.UUID

class `Assignment.kt` (
    val startTime: String,
    val EndTime: String,
    var day: LocalDateTime,
    val quotas: Int,
    var isActive: Boolean,
    val price: Float
) {
    val id: String = UUID.randomUUID().toString()

    fun join() =
    fun leave() =

}