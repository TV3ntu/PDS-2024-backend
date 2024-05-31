package ar.edu.unsam.pds.models

import jakarta.persistence.*
import org.springframework.data.rest.core.annotation.RestResource
import java.io.Serializable
import java.util.*

@Entity @Table(name = "APP_COURSE")
class Course(
    val title: String,

    @Column(length = 1024)
    val description: String,

    var category: String,
    var image: String
) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @RestResource(exported = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    val assignments = mutableSetOf<Assignment>()

    fun addAssignment(assignment: Assignment) {
        assignments.add(assignment)
        assignment.attachCourse(this)
    }
}