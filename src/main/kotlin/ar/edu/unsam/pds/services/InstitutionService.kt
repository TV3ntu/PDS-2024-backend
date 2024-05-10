package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service

@Service
class InstitutionService(
    private val institutionRepository: InstitutionRepository
) {

    fun getAll(): List<InstitutionResponseDto> {
        val institutions = institutionRepository.getAll()
        return institutions.map { Mapper.buildInstitutionDto(it) }
    }

    fun getInstitution(idInstitution: String): InstitutionDetailResponseDto {
        val institution = institutionRepository.findById(idInstitution)
        return Mapper.buildInstitutionDetailDto(institution as Institution)
    }

//    fun getCourses(idInstitution: String): List<InstitutionResponseDto> {
//        val institution = institutionRepository.findById(idInstitution) as Institution
//        return Mapper.buildInstitutionListDto(institution.courses)
//    }
}