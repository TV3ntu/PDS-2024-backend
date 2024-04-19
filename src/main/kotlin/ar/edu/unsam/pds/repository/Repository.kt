package ar.edu.unsam.pds.repository


abstract class Repository<T: Element> {

    private val collection = mutableMapOf<Int, T>()
    private var index = 1

    fun getAll(): MutableCollection<T> {
        return collection.values
    }

    fun create(element: T){
        collection[index] = element
        index++
    }


    fun deleteByObjectId(value: String) {
        val obj = findByObjectId(value)
        val keyToRemove = collection.filterValues { it == obj }.keys.first()
        collection.remove(keyToRemove)
    }

    fun update(id: String, element: T){
        val obj = collection.values.firstOrNull { it.findMe(id) }
        val indexToUpdate = collection.values.indexOf(obj)
        collection[indexToUpdate] = element
    }

    open fun findByObjectId(value: String): Element {
        return collection.values.firstOrNull { it.findMe(value) }
            ?: error("No se encontró el elemento con el valor $value")
    }

    fun clear(){
        collection.clear()
        index = 1
    }

    private fun indexExists(index: Int) {
        collection[index] ?: error("No se encontró el elemento con el índice $index")
    }
}

interface Element {
    fun findMe(value: String): Boolean
}