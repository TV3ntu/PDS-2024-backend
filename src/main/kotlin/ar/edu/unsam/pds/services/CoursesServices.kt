package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Service

@Service
class CoursesServices {
    private lateinit var institutionRepo: InstitutionRepository
    private lateinit var courseRepo: CourseRepository

    fun getCoursesAll(): MutableCollection<Course> {
        return courseRepo.getAll()
    }

    fun getCoursesList(idInstitution: String): MutableCollection<Course> {
        var institution = institutionRepo.findByObjectId(idInstitution)

        // return institution.getCourses()
        return mutableListOf()
    }

    fun getCourseItem(idCourse: String): Course {
        return courseRepo.findByObjectId(idCourse) as Course
    }
}