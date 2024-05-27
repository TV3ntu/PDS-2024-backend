package ar.edu.unsam.pds.models
import java.util.UUID
import jakarta.persistence.*

@Entity
class Institution (
    @Column(length = 30)
    val name: String,
    @Column
    val description: String,
    @Column
    val category: String,
    @Column
    var image: String
)   {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id : UUID

    @OneToMany(fetch=FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name="id_user", referencedColumnName = "id")
    val courses: MutableSet<Course> = mutableSetOf()

    fun addCourse(course: Course) {
        courses.add(course)
    }

    fun findMe(value: UUID): Boolean = id.equals(value)
}