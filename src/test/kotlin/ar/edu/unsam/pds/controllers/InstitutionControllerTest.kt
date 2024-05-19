package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.services.InstitutionService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class InstitutionControllerTest {
    @Mock
    private lateinit var institutionService: InstitutionService
    private lateinit var institutionController: InstitutionController

    @BeforeEach
    fun setUp() {
        institutionController = InstitutionController()
        institutionController.institutionService = institutionService
    }

    @Test
    fun `test get all institutions`() {
        val institutions = listOf(
            InstitutionResponseDto(
                id = "id",
                name = "name",
                description = "description",
                category = "category",
                image = "image"
            )
        )

        `when`(institutionService.getAll()).thenReturn(institutions)

        val responseEntity = institutionController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institutions)
    }

    @Test
    fun `test get a particular institution`() {
        val institution = InstitutionDetailResponseDto(
            id = "123",
            name = "name",
            description = "description",
            category = "category",
            image = "image",
            courses = mutableSetOf()
        )

        `when`(institutionService.getInstitution("123")).thenReturn(institution)

        val responseEntity = institutionController.getInstitution("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institution)
    }

//    @Test
//    fun `test get all the courses from an institution`() {
//        val courses = listOf(Course("title 1", "description", "category", ""))
//        `when`(institutionService.getCoursesOfInstitution("123")).thenReturn(courses)
//
//        val responseEntity = institutionController.getCoursesOfInstitution("123")
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == courses)
//    }
}