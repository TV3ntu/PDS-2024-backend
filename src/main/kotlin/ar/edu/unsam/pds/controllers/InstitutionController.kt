package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.InstitutionDto
import ar.edu.unsam.pds.services.InstitutionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/institutions")
@CrossOrigin("*")
class InstitutionController {

    @Autowired
    lateinit var institutionService: InstitutionService

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<InstitutionDto>> {
        return ResponseEntity.ok(institutionService.getAll())
    }
}