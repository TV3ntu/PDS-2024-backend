package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Institution
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "institutions", path = "institutions")
interface InstitutionRepository : JpaRepository<Institution, UUID> {
    @RestResource(exported = false)
    @Query("""
        SELECT i FROM Institution i
        WHERE i.name LIKE concat('%', :query, '%')
        OR i.description LIKE concat('%', :query, '%')
        OR i.category LIKE concat('%', :query, '%')
    """)
    fun getAllBy(@Param("query") query: String): MutableList<Institution>

    @RestResource(exported = false)
    @Query("""
        SELECT i FROM Institution i
        JOIN i.courses c
        WHERE c.id = :courseId
    """)
    fun findByCourseId(@Param("courseId") courseId: UUID): Institution

    @RestResource(exported = false)
    override fun findAll(pageable: Pageable): Page<Institution>

    @RestResource(exported = false)
    override fun findById(id: UUID): Optional<Institution>
}