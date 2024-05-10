package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.models.Assignment
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

    @GetMapping("")
    @Operation(summary = "Get all courses")
    fun getAll(): ResponseEntity<List<CourseResponseDto>> {
        return ResponseEntity.ok(courseServices.getAll())
    }

    @GetMapping("{idCourse}")
    @Operation(summary = "Get course by id")
    fun getCourse(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<CourseDetailResponseDto> {
        return ResponseEntity.ok(courseServices.getCourse(idCourse))
    }

    @GetMapping("{idCourse}/assignments")
    @Operation(summary = "Get all assignments by course")
    fun getAssignmentOfCourse(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<List<Assignment>> {
        return ResponseEntity.ok(courseServices.getAssignmentOfCourse(idCourse))
    }
}
