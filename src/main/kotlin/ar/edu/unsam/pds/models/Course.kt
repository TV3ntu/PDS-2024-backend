package ar.edu.unsam.pds.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity @Table(name = "APP_COURSE")
@EntityListeners(AuditingEntityListener::class)
class Course(
    val title: String,

    @Column(length = 1024)
    val description: String,

    var category: String,
    var image: String
) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    val assignments = mutableSetOf<Assignment>()

    @CreatedDate
    @Column(name = "REGISTER_DATE", nullable = false, updatable = false)
    lateinit var registerDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "LAST_UPDATE", nullable = false)
    lateinit var lastUpdate: LocalDateTime

    fun addAssignment(assignment: Assignment) {
        assignments.add(assignment)
        assignment.attachCourse(this)
    }

    fun totalIncome(): Double {
        return assignments.sumOf { it.totalIncome() }
    }

    fun mostPopularAssignment(): Assignment {
        return assignments.maxByOrNull { it.subscribedUsers.size }!!
    }

    fun mostProfitableAssignment(): Assignment {
        return assignments.maxByOrNull { it.totalIncome() }!!
    }

    fun totalSubscribedUsers(): Int {
        return assignments.sumOf { it.totalSubscribedUsers() }
    }

    fun assigmentsNames(): Set<String> {
        return assignments.map { it.name() }.toSet()
    }


}