package ar.edu.unsam.pds.models
import java.util.UUID

class Couse (
    val title: String,
    val description: String,
    var category: String
) {
    val id: String = UUID.randomUUID().toString()

    fun suscribe(assignment): Assignment =
    fun unSuscribe(assignment): Assignment =
}