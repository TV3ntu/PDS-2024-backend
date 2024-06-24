package ar.edu.unsam.pds.models

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "app_subscriptions")
class Subscription(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    var assignment: Assignment,

    @Column(name = "start_date")
    var startDate: LocalDateTime? = null,

    @OneToOne(mappedBy = "subscription", cascade = [CascadeType.ALL], orphanRemoval = true)
    var payment: Payment? = null

) : Timestamp(), Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    lateinit var id: UUID

    @Column(name = "end_date")
    var endDate: LocalDateTime? = null
    

}