package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.services.InstitutionService
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/institutions")
@CrossOrigin("*")
class InstitutionController {

    @Autowired
    lateinit var institutionService: InstitutionService

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<InstitutionResponseDto>> {
        return ResponseEntity.ok(institutionService.getAll())
    }

    @GetMapping("/{idInstitution}")
    fun institutionItem(
        @PathVariable @UUID idInstitution: String
    ): ResponseEntity<InstitutionResponseDto> {
        return ResponseEntity.ok(institutionService.getInstitutionItem(idInstitution))
    }
}