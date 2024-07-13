package ar.edu.unsam.pds.security.repository

import ar.edu.unsam.pds.security.models.Principal
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.session.FindByIndexNameSessionRepository
import org.springframework.stereotype.Repository

@Repository @Transactional
@RepositoryRestResource(exported = false)
class PrincipalRepositoryExtraImpl(
    private val sessionRepository: FindByIndexNameSessionRepository<*>,
) : PrincipalRepositoryExtra {
    @PersistenceContext private lateinit var entityManager: EntityManager

    override fun deleteWithDestructionOfAllSessions(principal: Principal) {
        principal.disable()
        entityManager.merge(principal)
        entityManager.flush()

        sessionRepository.findByPrincipalName(principal.username).map {
            sessionRepository.deleteById(it.key)
        }
    }

}