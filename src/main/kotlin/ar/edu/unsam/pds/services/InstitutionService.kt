package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.InstitutionDto
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Service

@Service
class InstitutionService {

    private var institutionRepository = InstitutionRepository

    fun getAll(): List<InstitutionDto> {
        val institutions = institutionRepository.getAll()

        return institutions.map { buildInstitutionDto(it) }
    }

    fun getInstitutionItem(idInstitution: String): InstitutionDto {
        val institution = institutionRepository.findByObjectId(idInstitution)
        return buildInstitutionDto(institution as Institution)
    }

    private fun buildInstitutionDto(institution: Institution): InstitutionDto {
        return InstitutionDto(institution.id, institution.name, institution.description, institution.category,
            institution.image, institution.getCourses())
    }
}