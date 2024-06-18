package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.InstitutionMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InstitutionServiceTest : BootstrapNBTest() {
    private lateinit var institutionService: InstitutionService

    @BeforeEach
    fun setUpInstitutionServiceTest() {
        institutionService = InstitutionService(institutionRepository, principalRepository)
    }

    @Test
    fun `test get all institutions`() {
        val obtainedValue = institutionService.getAll("").toList()
        val expectedValue = institutions.map {
            InstitutionMapper.buildInstitutionDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get dance institution`() {
        val obtainedValue = institutionService.getAll("Enchanted Dance").toList()

        val expectedValue = listOf(institutions[0]).map {
            InstitutionMapper.buildInstitutionDto(it)
        }

        assertEquals(expectedValue, obtainedValue)
    }

    @Test
    fun `test get mathematics institution`() {
        val obtainedValue = institutionService.getAll("mathematics ins").toList()

        val expectedValue = listOf(institutions[1]).map {
            InstitutionMapper.buildInstitutionDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get yoga institution`() {
        val obtainedValue = institutionService.getAll("yoga_category").toList()

        val expectedValue = listOf(institutions[2]).map {
            InstitutionMapper.buildInstitutionDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular institution`() {
        val uuid = institutions[0].id.toString()
        val obtainedValue = institutionService.getInstitution(uuid)
        val expectedValue = InstitutionMapper.buildInstitutionDetailDto(institutions[0])

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw get a particular institution`() {
        val uuid = "029ce681-9f90-45e7-af7f-e74a8cfb4b57"
        assertThrows<NotFoundException> {
            institutionService.getInstitution(uuid)
        }
    }
}