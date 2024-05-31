package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Assignment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "assignments", path = "assignments")
interface AssignmentRepository : JpaRepository<Assignment, UUID> {
    @RestResource(exported = false)
    override fun findAll(pageable: Pageable): Page<Assignment>

    @RestResource(exported = false)
    override fun findById(id: UUID): Optional<Assignment>
}