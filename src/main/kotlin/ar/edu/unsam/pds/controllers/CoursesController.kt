package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.CoursesServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@RestController @RequestMapping("api/")
@CrossOrigin(origins = ["*"], methods = [GET])
class CoursesController {
    @Autowired private lateinit var courseServices: CoursesServices

    @GetMapping(value = ["courses"])
    fun coursesAll(): MutableCollection<Course> {
        return courseServices.getCoursesAll()
    }

    @GetMapping(value = ["courses/{idInstitution}"])
    fun coursesList(
        @PathVariable idInstitution: String
    ): MutableCollection<Course> {
        return courseServices.getCoursesList(idInstitution)
    }

    @GetMapping("course/{idCourse}")
    fun courseItem(
        @PathVariable idCourse: String
    ): Course {
        return courseServices.getCourseItem(idCourse)
    }
}