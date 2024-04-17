package ar.edu.unsam.pds.models
import java.util.UUID

class Assignment (
    val name: String,
    val category: String
) {
    val id: String = UUID.randomUUID().toString()

    fun couseCreate() =
    fun couseDelete() =
    fun metrics() =
}