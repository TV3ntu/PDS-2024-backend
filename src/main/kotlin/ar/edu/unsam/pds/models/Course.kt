package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Couse (
    val title: String,
    val description: String,
    var category: String
) : Element {
    val id: String = UUID.randomUUID().toString()
}