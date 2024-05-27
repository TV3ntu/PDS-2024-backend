package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Institution
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InstitutionRepository : CrudRepository<Institution, String> {
//    fun getAllBy(query: String): MutableList<Institution> {
//        return this.getAll().filter {
//            it.name.contains(query) ||
//            it.description.contains(query) ||
//            it.category.contains(query)
//        }.toMutableList()
//    }
}