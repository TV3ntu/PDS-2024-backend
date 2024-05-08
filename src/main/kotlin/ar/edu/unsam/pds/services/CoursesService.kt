package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CoursesService {
    private var courses = CourseRepository

    fun getAll(): List<Course> {
        return courses.getAll().toList()
    }

    fun getAssignmentOfCourse(idCourse: String): List<Assignment> {
        val course = courses.findByObjectId(idCourse) as Course
        return course.getAssignments().toList()
    }

    fun getCourse(idCourse: String): Course {
        return courses.findByObjectId(idCourse) as Course
    }
}