package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.exceptions.NotFoundException
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
abstract class Repository<T: Element> {

    private val collection = mutableListOf<T>()

    fun getAll(): MutableCollection<T> {
        return collection
    }

    open fun create(element: T){
        collection.add(element)
    }


    fun delete(value: String) {
        val obj = findById(value).orElseThrow { NotFoundException("Usuario no encontrado") }
        collection.remove(obj)
    }

    fun update(id: String, element: T){
        val obj = collection.firstOrNull { it.findMe(id) }
        val indexToUpdate = collection.indexOf(obj)
        collection[indexToUpdate] = element
    }

    fun findById(value: String): Optional<T> {
        val user = collection.find { it.findMe(value) }
        return Optional.ofNullable(user)
    }

    fun clear(){
        collection.clear()
    }

    fun findRandomItem(): T? {
        return collection.randomOrNull()
    }
}

interface Element {
    fun findMe(value: String): Boolean
}