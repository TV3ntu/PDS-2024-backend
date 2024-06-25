package ar.edu.unsam.pds.exceptions.controllers

import ar.edu.unsam.pds.controllers.InstitutionController
import ar.edu.unsam.pds.exceptions.RestExceptionHandler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class InstitutionControllerDtosTest : ControllersDtosBasicTest() {
    @InjectMocks
    private lateinit var institutionController: InstitutionController

    @BeforeEach
    fun setUpInstitutionControllerDtosTest() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(institutionController)
            .setControllerAdvice(RestExceptionHandler())
            .build()
    }

    @Test
    fun `test get a particular institution`() {
        mockMvc.perform(
            get("/api/institutions/cuchuflito").contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test create a particular institution`() {
        mockMvc.perform(
            multipart("/api/institutions")
                .file(file)
                .param("name", "")
                .param("description", "description")
                .param("category", "category")
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El nombre no puede estar vacío")
//        )
    }

    @Test
    fun `test delete a particular institution`() {
        mockMvc.perform(
            delete("/api/institutions/cuchuflito").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }
}
