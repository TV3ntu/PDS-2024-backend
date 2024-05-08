package ar.edu.unsam.pds.controllers

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

    @GetMapping(value = ["/"])
    @Operation(summary = "Get all assignments")
    fun getAll(): ResponseEntity<List<Assignment>> {
        return ResponseEntity.ok(assignmentService.getAll())
    }

    @GetMapping(value = ["{idAssignment}"])
    @Operation(summary = "Get assignment by id")
    fun getAssignment(
        @PathVariable @UUID idAssignment: String
    ): ResponseEntity<Assignment> {
        return ResponseEntity.ok(assignmentService.getAssignment(idAssignment))
    }
}