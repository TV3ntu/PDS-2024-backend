package ar.edu.unsam.pds.repository

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
        val obj = findById(value)
        collection.remove(obj)
    }

    fun update(id: String, element: T){
        val obj = collection.firstOrNull { it.findMe(id) }
        val indexToUpdate = collection.indexOf(obj)
        collection[indexToUpdate] = element
    }

    fun findById(value: String): T {
        return collection.find { it.findMe(value) } ?: throw NoSuchElementException("No se encontraron elementos con ese valor")
    }

    fun clear(){
        collection.clear()
    }
}

interface Element {
    fun findMe(value: String): Boolean
}