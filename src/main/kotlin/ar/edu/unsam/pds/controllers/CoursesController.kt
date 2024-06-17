package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.CourseStatsResponseDto
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.CoursesService
import io.swagger.v3.oas.annotations.Operation
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/courses")
@CrossOrigin("*")
class CoursesController {
    @Autowired
    lateinit var courseServices: CoursesService

    @GetMapping("")
    @Operation(summary = "Get all courses")
    fun getAll(
        @RequestParam(required = false) query: String?
    ): ResponseEntity<List<CourseResponseDto>> {
        return ResponseEntity.ok(courseServices.getAll(query ?: ""))
    }

    @DeleteMapping("")
    @Operation(summary = "Delete multiple courses by ids")
    fun deleteMultipleCourses(
        @RequestBody @UUID idCourses: List<String>,
        @AuthenticationPrincipal principal : Principal
    ): ResponseEntity<Map<String, String>> {
        courseServices.deleteAllById(idCourses, principal)
        return ResponseEntity.ok(mapOf("message" to "Cursos eliminados correctamente."))
    }

    @DeleteMapping("{idCourse}")
    @Operation(summary = "Delete course by id")
    fun deleteCourse(
        @PathVariable @UUID idCourse: String,
        @AuthenticationPrincipal principal : Principal
    ): ResponseEntity<Map<String, String>> {
        courseServices.deleteCourse(idCourse, principal)
        return ResponseEntity.ok(mapOf("message" to "Curso eliminado correctamente."))
    }
        
    @PostMapping("")
    @Operation(summary = "Create a course")
    fun createCourse(
        @RequestBody course: CourseRequestDto
    ): ResponseEntity<CourseResponseDto> {
        return ResponseEntity.ok(courseServices.createCourse(course))
    }

    @GetMapping("{idCourse}")
    @Operation(summary = "Get course by id")
    fun getCourse(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<CourseDetailResponseDto> {
        return ResponseEntity.ok(courseServices.getCourse(idCourse))
    }

    @GetMapping("{idCourse}/stats")
    @Operation(summary = "Get course stats")
    fun getCourseStats(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<CourseStatsResponseDto> {
        return ResponseEntity.ok(courseServices.getCourseStats(idCourse))
    }
}
