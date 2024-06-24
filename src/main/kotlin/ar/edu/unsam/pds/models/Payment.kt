package ar.edu.unsam.pds.models

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "APP_PAYMENT")
class Payment(
    val amount: Double,
    val date: LocalDateTime,
    val status: String,
    val paymentMethod: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    val user: User,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="assignment_id", referencedColumnName = "id")
    val assignment: Assignment,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id", unique = true)
    var subscription: Subscription? = null

) : Timestamp(), Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

}