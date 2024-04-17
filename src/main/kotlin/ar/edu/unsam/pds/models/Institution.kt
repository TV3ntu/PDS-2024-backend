package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Institution (
    val name: String,
    val category: String,
    var image: String
) : Element  {
    val id: String = UUID.randomUUID().toString()
    override fun findMe(value: String): Boolean {
        TODO("Not yet implemented")
    }
}