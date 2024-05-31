package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Course
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
interface CourseRepository : JpaRepository<Course, UUID> {
    @RestResource(exported = false)
    @Query("""
        SELECT c FROM Course c
        WHERE c.title LIKE concat('%', :query, '%')
        OR c.description LIKE concat('%', :query, '%')
        OR c.category LIKE concat('%', :query, '%')
    """)
    fun getAllBy(@Param("query") query: String): MutableList<Course>

    @RestResource(exported = false)
    override fun findAll(pageable: Pageable): Page<Course>

    @RestResource(exported = false)
    override fun findById(id: UUID): Optional<Course>
}