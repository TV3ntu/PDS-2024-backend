package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.AssignmentRequestDto
import ar.edu.unsam.pds.dto.request.InstitutionRequestDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.InstitutionService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/institutions")
@CrossOrigin("*")
class InstitutionController {
    @Autowired
    lateinit var institutionService: InstitutionService

    @GetMapping("")
    @Operation(summary = "Get all institutions")
    fun getAll(
        @RequestParam(required = false) query: String?
    ): ResponseEntity<List<InstitutionResponseDto>> {
        return ResponseEntity.ok(institutionService.getAll(query ?: ""))
    }

    @GetMapping("{idInstitution}")
    @Operation(summary = "Get institution by id")
    fun getInstitution(
        @PathVariable @UUID idInstitution: String
    ): ResponseEntity<InstitutionDetailResponseDto> {
        return ResponseEntity.ok(institutionService.getInstitution(idInstitution))
    }

    @PostMapping("")
    @Operation(summary = "Create a institution")
    fun createInstitution(
        @RequestBody @Valid institution: InstitutionRequestDto,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<InstitutionResponseDto> {
        return ResponseEntity.ok(institutionService.createInstitution(institution, principal))
    }

    @DeleteMapping("{idInstitution}")
    @Operation(summary = "Delete institution by id")
    fun deleteInstitution(
        @PathVariable @UUID idInstitution: String,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<Map<String, String>> {
        institutionService.deleteInstitution(idInstitution, principal)
        return ResponseEntity.ok(mapOf("message" to "Institution eliminado correctamente."))
    }
}
