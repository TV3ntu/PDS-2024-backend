package ar.edu.unsam.pds.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity @Table(name = "APP_INSTITUTION")
@EntityListeners(AuditingEntityListener::class)
class Institution(
    val name: String,

    @Column(length = 1024)
    val description: String,

    val category: String,
    var image: String
) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="institution_id", referencedColumnName = "id")
    val courses: MutableSet<Course> = mutableSetOf()

    @CreatedDate
    @Column(name = "REGISTER_DATE", nullable = false, updatable = false)
    lateinit var registerDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "LAST_UPDATE", nullable = false)
    lateinit var lastUpdate: LocalDateTime

    fun addCourse(course: Course) {
        courses.add(course)
    }
}