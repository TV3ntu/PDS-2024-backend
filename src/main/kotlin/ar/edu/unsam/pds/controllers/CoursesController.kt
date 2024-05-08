package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.CoursesService
import io.swagger.v3.oas.annotations.Operation
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/courses")
@CrossOrigin("*")
class CoursesController {
    @Autowired
    lateinit var courseServices: CoursesService

    @GetMapping(value = ["/"])
    @Operation(summary = "Get all courses")
    fun getAll(): ResponseEntity<List<Course>> {
        return ResponseEntity.ok(courseServices.getAll())
    }

    @GetMapping(value = ["{idCourse}"])
    @Operation(summary = "Get course by id")
    fun getCourse(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<Course> {
        return ResponseEntity.ok(courseServices.getCourse(idCourse))
    }

    @GetMapping(value = ["{idCourse}/assignments"])
    @Operation(summary = "Get all assignments by course")
    fun getAssignmentOfCourse(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<List<Assignment>> {
        return ResponseEntity.ok(courseServices.getAssignmentOfCourse(idCourse))
    }
}