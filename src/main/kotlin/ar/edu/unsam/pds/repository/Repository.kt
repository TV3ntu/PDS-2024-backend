package ar.edu.unsam.pds.repository

abstract class Repository<T: Element> {

    val colection = mutableMapOf<Int, T>()
    private var index = 1

    fun getAll(): MutableCollection<T> {
        return colection.values
    }

    fun create(element: T){
        colection[index] = element
        index++
    }


    fun deleteByObjectId(value: String) {
        val obj = findByObjectId(value)
        val keyToRemove = colection.filterValues { it == obj }.keys.first()
        colection.remove(keyToRemove)
    }

    fun update(id: String, element: T){
        val obj = colection.values.firstOrNull { it.findMe(id) }
        val indexToUpdate = colection.values.indexOf(obj)
        colection[indexToUpdate] = element
    }

    open fun findByObjectId(value: String): Element {
        return colection.values.firstOrNull { it.findMe(value) }
            ?: throw NotFoundException("No se encontró el elemento con el valor $value")
    }

    fun clear(){
        colection.clear()
        index = 1
    }

    private fun indexExists(index: Int) {
        colection[index] ?: throw NotFoundException("No se encontró el elemento con el índice $index")
    }
}

interface Element {
    fun findMe(value: String): Boolean
}