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
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class InstitutionService(
    private val institutionRepository: InstitutionRepository,
    private val principalRepository: PrincipalRepository,
    private val userRepository: UserRepository,
    private val imageService: StorageService
) {

    fun getAll(query: String): List<InstitutionResponseDto> {
        val institutions = institutionRepository.getAllBy(query)
        return institutions.map { InstitutionMapper.buildInstitutionDto(it) }
    }

    fun getAllByPrincipal(query: String, principal: Principal): List<InstitutionResponseDto> {
        val institutions = institutionRepository.getAllByPrincipal(query, principal)
        return institutions.map { InstitutionMapper.buildInstitutionDto(it) }
    }

    fun getInstitution(idInstitution: String): InstitutionDetailResponseDto {
        val institution = findInstitutionById(idInstitution)
        return InstitutionMapper.buildInstitutionDetailDto(institution)
    }

    fun findInstitutionById(idInstitution: String): Institution {
        val uuid = UUID.fromString(idInstitution)
        return institutionRepository.findById(uuid).orElseThrow {
            NotFoundException("Institucion no encontrada para el uuid suministrado")
        }
    }

    fun findInstitutionByCourseId(courseId: UUID): Institution {
        return institutionRepository.findByCourseId(courseId)
    }

    @Transactional
    fun createInstitution(institution: InstitutionRequestDto, principal: Principal): InstitutionResponseDto {
        principal.getUser().isAdmin = true
        val imageName = imageService.savePublic(institution.file)
        val newInstitution = Institution(
            name = institution.name,
            description = institution.description,
            category = institution.category,
            image = imageName
        ).apply {
            addAdmin(principal.getUser())
        }
        userRepository.save(principal.getUser())
        principalRepository.save(principal)
        institutionRepository.save(newInstitution)

        return InstitutionMapper.buildInstitutionDto(newInstitution)
    }

    @Transactional
    fun deleteInstitution(idInstitution: String, principal: Principal) {
        val uuid = UUID.fromString(idInstitution)

        if (!institutionRepository.isOwner(uuid, principal)) {
            throw PermissionDeniedException("No se puede borrar un institucion de la cual no se es propietario")
        }

        if (!institutionRepository.isDeletable(UUID.fromString(idInstitution))) {
            throw ValidationException("No se puede eliminar una Institucion con usuarios inscriptos")
        }

        val institution = institutionRepository.findById(uuid).orElseThrow {
            NotFoundException("Institucion no encontrada")
        }

        val imageName = institution.image
        institutionRepository.delete(institution)
        imageService.deletePublic(imageName)
    }
}