package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.services.InstitutionService
import io.swagger.v3.oas.annotations.Operation
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

    @GetMapping("")
    @Operation(summary = "Get all institutions")
    fun getAll(): ResponseEntity<List<InstitutionResponseDto>> {
        return ResponseEntity.ok(institutionService.getAll())
    }

    @GetMapping("{idInstitution}")
    @Operation(summary = "Get institution by id")
    fun getInstitution(
        @PathVariable @UUID idInstitution: String
    ): ResponseEntity<InstitutionDetailResponseDto> {
        return ResponseEntity.ok(institutionService.getInstitution(idInstitution))
    }

//    @GetMapping("{idInstitution}/courses")
//    @Operation(summary = "Get all courses by institution")
//    fun getCourses(
//        @PathVariable @UUID idInstitution: String
//    ): ResponseEntity<List<InstitutionResponseDto>> {
//        val courses = institutionService.getCourses(idInstitution)
//        return ResponseEntity.ok().body(courses)
//    }
}
