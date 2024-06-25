package ar.edu.unsam.pds.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "APP_REVIEW", uniqueConstraints = [UniqueConstraint(columnNames = ["USER_ID", "COURSE_ID"])])
class Review(
    @ManyToOne
    val user: User,

    @ManyToOne
    val course: Course,

    val rating: Int,
    val description: String
) {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID
}