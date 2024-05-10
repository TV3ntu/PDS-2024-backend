package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.repository.Element
import java.util.UUID

class User(
    var name: String,
    var lastName: String,
    var email: String,
    var image: String,
) : Element {
    val id: String = UUID.randomUUID().toString()

    override fun findMe(value: String): Boolean = id == value
}