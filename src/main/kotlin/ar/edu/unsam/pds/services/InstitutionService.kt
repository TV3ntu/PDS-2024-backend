package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.InstitutionRequestDto
import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.PermissionDeniedException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.InstitutionMapper
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class InstitutionService(
    private val institutionRepository: InstitutionRepository,
    private val principalRepository: PrincipalRepository
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

    @Transactional
    fun createInstitution(institution: InstitutionRequestDto, principal: Principal): InstitutionResponseDto {
        val principalUser = principalRepository.findById(principal.id).orElseThrow { NotFoundException("No se pudo crear la institucion") }
        if (principalUser.user == null) throw NotFoundException("No se pudo crear la institucion")
        principalUser.user!!.isAdmin = true

        val newInstitution = Institution(
            name = institution.name,
            description = institution.description,
            category = institution.category,
            image = institution.image
        ).apply {
            addAdmin(principalUser.user!!)
        }
        principalRepository.save(principalUser)
        institutionRepository.save(newInstitution)
        return InstitutionMapper.buildInstitutionDto(newInstitution)
    }

    @Transactional
    fun deleteInstitution(idInstitution: String, principal: Principal) {
        val isOwner = institutionRepository.isOwner(UUID.fromString(idInstitution), principal)
        if (!isOwner) throw PermissionDeniedException("Acceso denegado")
        val institution = institutionRepository.findById(UUID.fromString(idInstitution)).orElseThrow { NotFoundException("Institucion no encontrada") }
        if (!institutionRepository.isDeleteable(UUID.fromString(idInstitution))) throw ValidationException("No se puede eliminar una Institucion con usuarios inscriptos")
        institutionRepository.delete(institution)
    }
}