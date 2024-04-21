package ar.edu.unsam.pds.models
import java.util.UUID
import ar.edu.unsam.pds.repository.Element

class Institution (
    val name: String,
    val category: String,
    var image: String
) : Element  {
    val id: String = UUID.randomUUID().toString()
    private val courses: MutableSet<Course> = mutableSetOf()

    fun getCourses(): MutableSet<Course> {
        return courses
    }

    fun addCourse(course: Course) {
        courses.add(course)
    }

    override fun findMe(value: String): Boolean {
        TODO("Not yet implemented")
    }
}