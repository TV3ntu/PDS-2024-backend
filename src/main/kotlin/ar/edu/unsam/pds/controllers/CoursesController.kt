package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.CoursesService
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@RestController @RequestMapping("api/") @Validated
@CrossOrigin(origins = ["*"], methods = [GET])
class CoursesController {
    @Autowired
    private lateinit var courseServices: CoursesService

    @GetMapping(value = ["courses"])
    fun coursesAll(): List<Course> {
        return courseServices.getCoursesAll()
    }

    @GetMapping(value = ["courses/{idInstitution}"])
    fun coursesList(
        @PathVariable @UUID idInstitution: String
    ): List<Course> {
        return courseServices.getCoursesList(idInstitution)
    }

    @GetMapping(value = ["course/{idCourse}"])
    fun courseItem(
        @PathVariable @UUID idCourse: String
    ): Course {
        return courseServices.getCourseItem(idCourse)
    }
}