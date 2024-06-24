package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.InstitutionRequestDto
import ar.edu.unsam.pds.mappers.InstitutionMapper
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.InstitutionService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.util.*

@ExtendWith(MockitoExtension::class)
class InstitutionControllerTest {
    @Mock
    private lateinit var institutionService: InstitutionService
    private lateinit var institutionController: InstitutionController

    private lateinit var institution: Institution
    private lateinit var principal: Principal
    private lateinit var user: User
    private lateinit var uuid: String

    val fileImg: MultipartFile = MockMultipartFile("file", "filename.jpg", "text/plain", "some content".toByteArray())

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

        user = User(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = "",
            credits = 100000.0,
            isAdmin = true
        )

        principal = Principal().apply {
            id = UUID.randomUUID()
            username = this@InstitutionControllerTest.user.email
            password = "123"
            user = this@InstitutionControllerTest.user
            this.initProperties()
        }
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

    @Test
    fun `test create a particular institution`() {
        val institutionRes = InstitutionMapper.buildInstitutionDto(institution)
        val institutionReq = InstitutionRequestDto(
            name = institution.name,
            description = institution.description,
            category = institution.category,
            file = fileImg
        )

        `when`(institutionService.createInstitution(institutionReq, principal)).thenReturn(institutionRes)

        val responseEntity = institutionController.createInstitution(institutionReq, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institutionRes)
    }

    @Test
    fun `test delete a particular institution`() {
        `when`(institutionService.deleteInstitution(uuid, principal)).then { }

        val responseEntity = institutionController.deleteInstitution(uuid, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == mapOf("message" to "Institution eliminado correctamente."))
    }
}