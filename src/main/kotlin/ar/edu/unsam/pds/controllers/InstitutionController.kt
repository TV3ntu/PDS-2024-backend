package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.InstitutionDto
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.services.InstitutionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/institutions")
class InstitutionController {

    @Autowired
    lateinit var institutionService: InstitutionService

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<InstitutionDto>> {
        return ResponseEntity.ok(institutionService.getAll())
    }
}