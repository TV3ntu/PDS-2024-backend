package ar.edu.unsam.pds.exceptions.controllers

import ar.edu.unsam.pds.controllers.AssignmentController
import ar.edu.unsam.pds.exceptions.RestExceptionHandler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class AssignmentControllerDtosTest {
    private lateinit var mockMvc: MockMvc

    @InjectMocks
    private lateinit var assignmentController: AssignmentController

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(assignmentController)
            .setControllerAdvice(RestExceptionHandler())
            .build()
    }

    @Test
    fun `test assignmentItem`() {
        mockMvc.perform(
            get("/api/assignments/cuchuflito").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test subscribe to assignment - 1`() {
        mockMvc.perform(
            post("/api/assignments/subscribe").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "idUser": "cuchuflito",
                    "idAssignment": "88a014a8-95b5-40d1-9b67-92ad662e3c10"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("UUID de usuario debe ser valido")
//        )
    }

    @Test
    fun `test subscribe to assignment - 2`() {
        mockMvc.perform(
            post("/api/assignments/subscribe").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "idUser": "88a014a8-95b5-40d1-9b67-92ad662e3c10",
                    "idAssignment": "cuchuflito"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("UUID de clase debe ser valido")
//        )
    }

    @Test
    fun `test unsubscribe to assignment - 1`() {
        mockMvc.perform(
            post("/api/assignments/unsubscribe").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "idUser": "cuchuflito",
                    "idAssignment": "88a014a8-95b5-40d1-9b67-92ad662e3c10"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("UUID de usuario debe ser valido")
//        )
    }

    @Test
    fun `test unsubscribe to assignment - 2`() {
        mockMvc.perform(
            post("/api/assignments/unsubscribe").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "idUser": "88a014a8-95b5-40d1-9b67-92ad662e3c10",
                    "idAssignment": "cuchuflito"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("UUID de clase debe ser valido")
//        )
    }

    @Test
    fun `test create a particular assignment - incorrect id`() {
        mockMvc.perform(
            post("/api/assignments").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "idCourse": "",
                    "quotas": 66,
                    "price": 666.66,
                    "schedule": {
                        "days": ["MONDAY"],
                        "startTime": "12:00:00",
                        "endTime": "14:00:00",
                        "startDate": "2023-03-01",
                        "endDate": "2025-03-30",
                        "recurrenceWeeks": "MONTHLY"
                    }
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El ID no debe estar en blanco")
//        )
    }

    @Test
    fun `test create a particular assignment - incorrect quotas`() {
        mockMvc.perform(
            post("/api/assignments").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "idCourse": "88a014a8-95b5-40d1-9b67-92ad662e3c10",
                    "quotas": 0,
                    "price": 666.66,
                    "schedule": {
                        "days": ["MONDAY"],
                        "startTime": "12:00:00",
                        "endTime": "14:00:00",
                        "startDate": "2023-03-01",
                        "endDate": "2025-03-30",
                        "recurrenceWeeks": "MONTHLY"
                    }
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("Minimo deberia ser 1 clase")
//        )
    }

    @Test
    fun `test delete a particular assignment`() {
        mockMvc.perform(
            delete("/api/assignments/cuchuflito").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }
}