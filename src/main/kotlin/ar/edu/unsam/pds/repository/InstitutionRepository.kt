package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Institution
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface InstitutionRepository : JpaRepository<Institution, UUID> {
    @Query("""
        SELECT i FROM Institution i
        WHERE i.name LIKE concat('%', :query, '%')
        OR i.description LIKE concat('%', :query, '%')
        OR i.category LIKE concat('%', :query, '%')
    """)
    fun getAllBy(@Param("query") query: String): MutableList<Institution>
}