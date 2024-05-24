package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
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
        return institutionRepository.findById(idInstitution).map {
            return@map Mapper.buildInstitutionDetailDto(it)
        }.orElseThrow {
            NotFoundException("Institucion no encontrada")
        }
    }
}