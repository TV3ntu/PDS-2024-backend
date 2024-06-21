package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface PaymentRepository : JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p WHERE p.user.id = :userId AND p.assignment.id = :assignmentId ORDER BY p.date DESC LIMIT 1")
    fun findLastPaymentByUserIdAndAssignmentId(userId: UUID, assignmentId: UUID): Optional<Payment>
}