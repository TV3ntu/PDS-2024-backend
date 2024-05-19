package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Course
import org.springframework.data.domain.PageRequest

@org.springframework.stereotype.Repository
class CourseRepository : Repository<Course>() {
    fun getAllBy(queryOf: String): MutableList<Course> {
        return this.getAll().filter {
            it.title.contains(queryOf)  ||
            it.description.contains(queryOf) ||
            it.category.contains(queryOf)
        }.toMutableList()
    }
}