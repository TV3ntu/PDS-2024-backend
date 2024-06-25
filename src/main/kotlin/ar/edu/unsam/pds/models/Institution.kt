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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "app_user_institution",
        joinColumns = [JoinColumn(name = "institution_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val admin = mutableSetOf<User>()

    fun addCourse(course: Course) {
        courses.add(course)
    }

    fun addAdmin(user: User) {
        admin.add(user)
    }

}