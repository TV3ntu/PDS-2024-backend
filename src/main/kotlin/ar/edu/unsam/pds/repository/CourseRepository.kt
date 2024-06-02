package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface CourseRepository : JpaRepository<Course, UUID> {
    @Query("""
        SELECT c FROM Course c
        WHERE c.title ILIKE %:query%
        OR c.description ILIKE %:query%
        OR c.category ILIKE %:query%
    """)
    fun getAllBy(@Param("query") query: String): MutableList<Course>
}