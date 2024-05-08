package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.CoursesService
import io.swagger.v3.oas.annotations.Operation
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@RequestMapping("api/courses")
@CrossOrigin("*")
class CoursesController {

    @Autowired
    lateinit var courseServices: CoursesService

    @GetMapping("/")
    @Operation(summary = "Get all courses")
    fun coursesAll(): ResponseEntity<List<Course>> {
        return ResponseEntity.ok(courseServices.getCoursesAll())
    }

    @GetMapping(value = ["/institution/{idInstitution}"])
    @Operation(summary = "Get all courses by institution")
    fun coursesList(
        @PathVariable @UUID idInstitution: String
    ): ResponseEntity<List<Course>> {
        return ResponseEntity.ok(courseServices.getCoursesList(idInstitution))
    }

    @GetMapping(value = ["/{idCourse}"])
    @Operation(summary = "Get course by id")
    fun courseItem(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<Course> {
        return ResponseEntity.ok(courseServices.getCourseItem(idCourse))
    }
}