package ar.edu.unsam.pds.models

import jakarta.persistence.*
import org.springframework.data.rest.core.annotation.RestResource
import java.io.Serializable
import java.util.*

@Entity @Table(name = "APP_INSTITUTION")
class Institution(
    val name: String,
    val description: String,
    val category: String,
    var image: String
) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @RestResource(exported = false)
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="institution_id", referencedColumnName = "id")
    val courses: MutableSet<Course> = mutableSetOf()

    fun addCourse(course: Course) {
        courses.add(course)
    }
}