package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Institution

@org.springframework.stereotype.Repository
class InstitutionRepository : Repository<Institution>() {
    fun getAllBy(query: String): MutableList<Institution> {
        return this.getAll().filter {
            it.name.contains(query) ||
            it.description.contains(query) ||
            it.category.contains(query)
        }.toMutableList()
    }
}