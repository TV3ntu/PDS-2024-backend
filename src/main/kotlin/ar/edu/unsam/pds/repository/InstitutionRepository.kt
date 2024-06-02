package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Institution
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface InstitutionRepository : JpaRepository<Institution, UUID> {
    @Query("""
        SELECT i FROM Institution i
        WHERE i.name ILIKE %:query%
        OR i.description ILIKE %:query%
        OR i.category ILIKE %:query%
    """)
    fun getAllBy(@Param("query") query: String): MutableList<Institution>

    @Query("""
        SELECT i FROM Institution i
        JOIN i.courses c
        WHERE c.id = :courseId
    """)
    fun findByCourseId(@Param("courseId") courseId: UUID): Institution
}