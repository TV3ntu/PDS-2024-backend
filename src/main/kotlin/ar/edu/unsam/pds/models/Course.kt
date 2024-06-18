package ar.edu.unsam.pds.models

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity @Table(name = "APP_COURSE")
class Course(
    val title: String,

    @Column(length = 1024)
    val description: String,

    var category: String,
    @Column(length = 1024)
    var image: String
) : Timestamp(), Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = [CascadeType.ALL], orphanRemoval = true)
    val assignments = mutableSetOf<Assignment>()

    fun addAssignment(assignment: Assignment) {
        assignments.add(assignment)
        assignment.attachCourse(this)
    }

    fun removeAssignment(assignment: Assignment) {
        assignments.removeIf{ it.id == assignment.id }
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