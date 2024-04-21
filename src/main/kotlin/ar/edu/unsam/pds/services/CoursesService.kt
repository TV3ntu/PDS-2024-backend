package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Service

@Service
class CoursesService {
    private var institutions = InstitutionRepository
    private var courses = CourseRepository

    fun getCoursesAll(): List<Course> {
        return courses.getAll().toList()
    }

    fun getCoursesList(idInstitution: String): List<Course> {
        val institution = institutions.findByObjectId(idInstitution) as Institution
        return institution.getCourses().toList()
    }

    fun getCourseItem(idCourse: String): Course {
        return courses.findByObjectId(idCourse) as Course
    }
}