package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.repository.Element
import java.util.UUID

class User(
    val name: String,
    val lastName: String,
    val email: String,
    val image: String,
) : Element {
    val id: String = UUID.randomUUID().toString()

    override fun findMe(value: String): Boolean = id == value
}