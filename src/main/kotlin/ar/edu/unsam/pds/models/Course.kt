package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Course (
    val title: String,
    val description: String,
    var category: String,
    var image: String
) : Element {
    val id: String = UUID.randomUUID().toString()

    override fun findMe(value: String): Boolean {
        TODO("Not yet implemented")
    }
}