package ar.edu.unsam.pds.security.repository

import ar.edu.unsam.pds.security.models.Principal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface PrincipalRepository : JpaRepository<Principal, UUID> {
    @Query("SELECT p FROM Principal p WHERE p.user.email = :email")
    fun findUserByEmail(@Param("email") userEmail: String): Optional<Principal>
}