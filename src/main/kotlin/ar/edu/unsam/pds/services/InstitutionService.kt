package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.InstitutionMapper
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstitutionService(
    private val institutionRepository: InstitutionRepository
) {

    fun getAll(query: String): List<InstitutionResponseDto> {
        val institutions = institutionRepository.getAllBy(query)
        return institutions.map { InstitutionMapper.buildInstitutionDto(it) }
    }

    fun getInstitution(idInstitution: String): InstitutionDetailResponseDto {
        val institution = findInstitutionById(idInstitution)
        return InstitutionMapper.buildInstitutionDetailDto(institution)
    }

    fun findInstitutionById(idInstitution: String): Institution {
        val uuid = UUID.fromString(idInstitution)
        return institutionRepository.findById(uuid).orElseThrow {
            NotFoundException("Institucion no encontrada")
        }
    }

    fun findInstitutionByCourseId(courseId: UUID): Institution {
        return institutionRepository.findByCourseId(courseId)
    }
}