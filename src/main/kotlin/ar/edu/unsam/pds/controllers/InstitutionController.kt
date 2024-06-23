package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.InstitutionRequestDto
import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.InstitutionService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/institutions")
@CrossOrigin("*")
class InstitutionController : UUIDValid() {
    @Autowired lateinit var institutionService: InstitutionService

    @GetMapping("")
    @Operation(summary = "Get all institutions")
    fun getAll(
        @RequestParam(required = false) query: String?
    ): ResponseEntity<List<InstitutionResponseDto>> {
        return ResponseEntity.ok(institutionService.getAll(query ?: ""))
    }

    @GetMapping("admin")
    @Operation(summary = "Get all institutions")
    fun getAllByPrincipal(
        @RequestParam(required = false) query: String?,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<List<InstitutionResponseDto>> {
        return ResponseEntity.ok(institutionService.getAllByPrincipal(query ?: "", principal))
    }

    @GetMapping("{idInstitution}")
    @Operation(summary = "Get institution by id")
    fun getInstitution(
        @PathVariable idInstitution: String
    ): ResponseEntity<InstitutionDetailResponseDto> {
        this.validatedUUID(idInstitution)
        return ResponseEntity.ok(institutionService.getInstitution(idInstitution))
    }

    @PostMapping(value = [""], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "Create a institution")
    fun createInstitution(
        @ModelAttribute @Valid institution: InstitutionRequestDto,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<InstitutionResponseDto> {
        return ResponseEntity.ok(institutionService.createInstitution(institution, principal))
    }

    @DeleteMapping("{idInstitution}")
    @Operation(summary = "Delete institution by id")
    fun deleteInstitution(
        @PathVariable idInstitution: String,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<Map<String, String>> {
        this.validatedUUID(idInstitution)
        institutionService.deleteInstitution(idInstitution, principal)
        return ResponseEntity.ok(mapOf("message" to "Institution eliminado correctamente."))
    }
}
