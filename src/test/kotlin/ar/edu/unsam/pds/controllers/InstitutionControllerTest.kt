package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.mappers.InstitutionMapper
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.services.InstitutionService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class InstitutionControllerTest {
    @Mock
    private lateinit var institutionService: InstitutionService
    private lateinit var institutionController: InstitutionController

    private lateinit var institution: Institution
    private lateinit var uuid: String

    @BeforeEach
    fun setUp() {
        institutionController = InstitutionController()
        institutionController.institutionService = institutionService

        institution = Institution(
            name = "name",
            description = "description",
            category = "category",
            image = "image"
        ).apply {
            id = UUID.randomUUID()
        }

        uuid = institution.id.toString()
    }

    @Test
    fun `test get all institutions - no query`() {
        val institutions = listOf(InstitutionMapper.buildInstitutionDto(institution))

        `when`(institutionService.getAll("")).thenReturn(institutions)

        val responseEntity = institutionController.getAll(null)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institutions)
    }

    @Test
    fun `test get all institutions - query`() {
        val institutions = listOf(InstitutionMapper.buildInstitutionDto(institution))

        `when`(institutionService.getAll("query")).thenReturn(institutions)

        val responseEntity0 = institutionController.getAll("query")

        assert(responseEntity0.statusCode == HttpStatus.OK)
        assert(responseEntity0.body == institutions)
    }

    @Test
    fun `test get a particular institution`() {
        val institution = InstitutionMapper.buildInstitutionDetailDto(institution)

        `when`(institutionService.getInstitution(uuid)).thenReturn(institution)

        val responseEntity = institutionController.getInstitution(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institution)
    }
}