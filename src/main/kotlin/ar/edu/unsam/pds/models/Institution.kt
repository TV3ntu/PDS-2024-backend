package ar.edu.unsam.pds.models

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity @Table(name = "APP_INSTITUTION")
class Institution(
    val name: String,

    @Column(length = 1024)
    val description: String,

    val category: String,
    var image: String
) : Timestamp(), Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="institution_id", referencedColumnName = "id")
    val courses: MutableSet<Course> = mutableSetOf()

    fun addCourse(course: Course) {
        courses.add(course)
    }
}