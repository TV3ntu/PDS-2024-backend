package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Institution (
    val name: String,
    val category: String
) : Element  {
    val id: String = UUID.randomUUID().toString()

    //fun couseCreate() =
    //fun couseDelete() =
    //fun metrics() =
}