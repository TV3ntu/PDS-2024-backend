package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstitutionService(
    private val institutionRepository: InstitutionRepository
) {

    fun getAll(query: String): List<InstitutionResponseDto> {
        val institutions = institutionRepository.getAllBy(query)
        return institutions.map { Mapper.buildInstitutionDto(it) }
    }

    fun getInstitution(idInstitution: String): InstitutionDetailResponseDto {
        val institution = findInstitutionById(idInstitution)
        return Mapper.buildInstitutionDetailDto(institution)
    }

    fun findInstitutionById(idInstitution: String): Institution {
        val uuid = UUID.fromString(idInstitution)
        return institutionRepository.findById(uuid).orElseThrow {
            NotFoundException("Institucion no encontrada")
        }
    }

    fun findInstitutionByCourseId(courseId: String): Institution {
        return institutionRepository.findByCourseId(courseId)
    }
}