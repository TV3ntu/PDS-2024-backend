package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.AssigmentResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.services.AssignmentService
import io.swagger.v3.oas.annotations.Operation
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/assignments")
@CrossOrigin("*")
class AssignmentController {

    @Autowired
    lateinit var assignmentService: AssignmentService

    @GetMapping("/")
    @Operation(summary = "Get all assignments")
    fun assignmentAll(): ResponseEntity<List<AssigmentResponseDto>> {
        return ResponseEntity.ok(assignmentService.getAll())
    }

    @GetMapping(value = ["/course/{idCourse}"])
    @Operation(summary = "Get all assignments by course")
    fun assignmentList(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<List<Assignment>> {
        return ResponseEntity.ok(assignmentService.getAssignmentList(idCourse))
    }

    @GetMapping(value = ["/{idAssignment}"])
    @Operation(summary = "Get assignment by id")
    fun assignmentItem(
        @PathVariable @UUID idAssignment: String
    ): ResponseEntity<AssigmentResponseDto> {
        return ResponseEntity.ok(assignmentService.getAssignmentItem(idAssignment))
    }
}