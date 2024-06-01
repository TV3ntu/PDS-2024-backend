package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.utils.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class InstitutionServiceTest {
    @Autowired private lateinit var institutionRepository: InstitutionRepository
    private lateinit var institutionService: InstitutionService

    private lateinit var danceInstitution: Institution
    private lateinit var mathematicsInstitution: Institution
    private lateinit var yogaInstitution: Institution

    @BeforeEach
    fun setUp() {
        institutionService = InstitutionService(institutionRepository)

        danceInstitution = Institution(
            name = "Enchanted Dance",
            description = "dance institution",
            category = "dance_category",
            image = ""
        )

        mathematicsInstitution = Institution(
            name = "The Kingdom of Calculations",
            description = "mathematics institution",
            category = "mathematics_category",
            image = ""
        )

        yogaInstitution = Institution(
            name = "Serenity and Postures",
            description = "yoga institution",
            category = "yoga_category",
            image = ""
        )

        danceInstitution = institutionRepository.save(danceInstitution)
        mathematicsInstitution = institutionRepository.save(mathematicsInstitution)
        yogaInstitution = institutionRepository.save(yogaInstitution)
    }

    @Test
    fun `test get all institutions`() {
        val obtainedValue = institutionService.getAll("").toList()
        val expectedValue = listOf(danceInstitution, mathematicsInstitution, yogaInstitution).map {
            Mapper.buildInstitutionDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get dance institution`() {
        val obtainedValue = institutionService.getAll("Enchanted Dance").toList()

        val expectedValue = listOf(danceInstitution).map {
            Mapper.buildInstitutionDto(it)
        }

        assertEquals(expectedValue, obtainedValue)
    }

    @Test
    fun `test get mathematics institution`() {
        val obtainedValue = institutionService.getAll("mathematics ins").toList()

        val expectedValue = listOf(mathematicsInstitution).map {
            Mapper.buildInstitutionDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get yoga institution`() {
        val obtainedValue = institutionService.getAll("yoga_category").toList()

        val expectedValue = listOf(yogaInstitution).map {
            Mapper.buildInstitutionDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular institution`() {
        val obtainedValue = institutionService.getInstitution(danceInstitution.id.toString())
        val expectedValue = Mapper.buildInstitutionDetailDto(danceInstitution)

        assertEquals(obtainedValue, expectedValue)
    }
}