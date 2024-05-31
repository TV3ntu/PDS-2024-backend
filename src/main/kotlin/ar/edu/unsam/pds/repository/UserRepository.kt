package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.User
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
interface UserRepository : JpaRepository<User, UUID> {
    @RestResource(exported = false)
    override fun findAll(pageable: Pageable): Page<User>

    @RestResource(exported = false)
    override fun findById(id: UUID): Optional<User>

    @Operation(summary = "Deletion of a particular user")
    override fun deleteById(id: UUID)
}