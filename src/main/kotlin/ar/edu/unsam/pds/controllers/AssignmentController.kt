package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.services.AssignmentService
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@RequestMapping("api/")
@Validated
@CrossOrigin(origins = ["*"], methods = [GET])
class AssignmentController {
    @Autowired
    private lateinit var assignmentService: AssignmentService

    @GetMapping(value = ["assignments"])
    fun assignmentAll(): ResponseEntity<List<Assignment>> {
        return ResponseEntity.ok(assignmentService.getAssignmentAll())
    }

    @GetMapping(value = ["assignments/course/{idCourse}"])
    fun assignmentList(
        @PathVariable @UUID idCourse: String
    ): ResponseEntity<List<Assignment>> {
        return ResponseEntity.ok(assignmentService.getAssignmentList(idCourse))
    }

    @GetMapping(value = ["assignment/{idAssignment}"])
    fun assignmentItem(
        @PathVariable @UUID idAssignment: String
    ): ResponseEntity<Assignment> {
        return ResponseEntity.ok(assignmentService.getAssignmentItem(idAssignment))
    }
}