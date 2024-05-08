package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Service

@Service
class InstitutionService {

    private var institutionRepository = InstitutionRepository

    fun getAll(): List<InstitutionResponseDto> {
        val institutions = institutionRepository.getAll()

        return institutions.map { buildInstitutionDto(it) }
    }

    fun getInstitutionItem(idInstitution: String): InstitutionResponseDto {
        val institution = institutionRepository.findByObjectId(idInstitution)
        return buildInstitutionDto(institution as Institution)
    }

    private fun buildInstitutionDto(institution: Institution): InstitutionResponseDto {
        return InstitutionResponseDto(institution.id, institution.name, institution.description, institution.category,
            institution.image, institution.getCourses())
    }
}