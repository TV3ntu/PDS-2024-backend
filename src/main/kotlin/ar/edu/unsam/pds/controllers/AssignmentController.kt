package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.request.SubscribeRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.services.AssignmentService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
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

    @GetMapping("")
    @Operation(summary = "Get all assignments")
    fun getAll(): ResponseEntity<List<AssignmentResponseDto>> {
        return ResponseEntity.ok(assignmentService.getAll())
    }

    @GetMapping("{idAssignment}")
    @Operation(summary = "Get assignment by id")
    fun getAssignment(
        @PathVariable @UUID idAssignment: String
    ): ResponseEntity<AssignmentResponseDto> {
        return ResponseEntity.ok(assignmentService.getAssignment(idAssignment))
    }

    @PostMapping("/subscribe")
    @Operation(summary = "A user subscribes to a assignment")
    fun subscribeToAssignment(
        @RequestBody @Valid subscribeRequestDto: SubscribeRequestDto
    ): ResponseEntity<SubscribeResponseDto> {
        val idUser = subscribeRequestDto.idUser
        val idAssignment = subscribeRequestDto.idAssignment
        return ResponseEntity.ok(assignmentService.subscribe(idUser, idAssignment))
    }

    @PostMapping("/unsubscribe")
    @Operation(summary = "A user unsubscribes to a assignment")
    fun unsubscribeToAssignment(
        @RequestBody @Valid subscribeRequestDto: SubscribeRequestDto
    ): ResponseEntity<SubscribeResponseDto> {
        val idUser = subscribeRequestDto.idUser
        val idAssignment = subscribeRequestDto.idAssignment
        return ResponseEntity.ok(assignmentService.unsubscribe(idUser, idAssignment))
    }

    @PostMapping("")
    @Operation(summary = "Create a assignment")
    fun createAssignment(
        @RequestBody @Valid assignment: AssignmentRequestDto
    ): ResponseEntity<AssignmentResponseDto> {
        return ResponseEntity.ok(assignmentService.createAssignment(assignment))
    }

}