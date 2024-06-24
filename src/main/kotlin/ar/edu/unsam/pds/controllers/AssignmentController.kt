package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.request.SubscribeRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.dto.response.UserSubscribedResponseDto
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.AssignmentService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/assignments")
@CrossOrigin("*")
class AssignmentController : UUIDValid() {
    @Autowired lateinit var assignmentService: AssignmentService

    @GetMapping("")
    @Operation(summary = "Get all assignments")
    fun getAll(): ResponseEntity<List<AssignmentResponseDto>> {
        return ResponseEntity.ok(assignmentService.getAll())
    }

    @GetMapping("{idAssignment}")
    @Operation(summary = "Get assignment by id")
    fun getAssignment(
        @PathVariable idAssignment: String
    ): ResponseEntity<AssignmentResponseDto> {
        this.validatedUUID(idAssignment)
        return ResponseEntity.ok(assignmentService.getAssignment(idAssignment))
    }

    @PostMapping("/subscribe")
    @Operation(summary = "A user subscribes to a assignment")
    fun subscribeToAssignment(
        @RequestBody @Valid subscribeRequestDto: SubscribeRequestDto
    ): ResponseEntity<SubscribeResponseDto> {
        this.validatedUUID(subscribeRequestDto.idUser)
        this.validatedUUID(subscribeRequestDto.idAssignment)

        val idUser = subscribeRequestDto.idUser!!
        val idAssignment = subscribeRequestDto.idAssignment!!

        return ResponseEntity.ok(assignmentService.subscribe(idUser, idAssignment))
    }

    @PostMapping("/unsubscribe")
    @Operation(summary = "A user unsubscribes to a assignment")
    fun unsubscribeToAssignment(
        @RequestBody @Valid subscribeRequestDto: SubscribeRequestDto
    ): ResponseEntity<SubscribeResponseDto> {
        this.validatedUUID(subscribeRequestDto.idUser)
        this.validatedUUID(subscribeRequestDto.idAssignment)

        val idUser = subscribeRequestDto.idUser!!
        val idAssignment = subscribeRequestDto.idAssignment!!

        return ResponseEntity.ok(assignmentService.unsubscribe(idUser, idAssignment))
    }

    @PostMapping("")
    @Operation(summary = "Create a assignment")
    fun createAssignment(
        @RequestBody @Valid assignment: AssignmentRequestDto
    ): ResponseEntity<AssignmentResponseDto> {
        return ResponseEntity.ok(assignmentService.createAssignment(assignment))
    }

    @DeleteMapping("{idAssignment}")
    @Operation(summary = "Delete assignment by id")
    fun deleteAssignment(
        @PathVariable idAssignment: String,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<Map<String, String>> {
        this.validatedUUID(idAssignment)
        assignmentService.deleteAssignment(idAssignment, principal)
        return ResponseEntity.ok(mapOf("message" to "Assignment eliminado correctamente."))
    }

    @GetMapping("{idAssignment}/admin")
    @Operation(summary = "get users subscribed to assignment for administrator")
    fun getAssignmentSuscribedUsers(
        @PathVariable idAssignment: String
    ): ResponseEntity<List<UserSubscribedResponseDto>> {
        this.validatedUUID(idAssignment)
        return ResponseEntity.ok(assignmentService.getAssignmentSuscribedUsers(idAssignment))
    }

}