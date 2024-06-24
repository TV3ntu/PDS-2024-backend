package ar.edu.unsam.pds.exceptions.controllers

import ar.edu.unsam.pds.controllers.CoursesController
import ar.edu.unsam.pds.exceptions.RestExceptionHandler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class CourseControllerDtosTest : ControllersDtosBasicTest() {
    @InjectMocks
    private lateinit var coursesController: CoursesController

    @BeforeEach
    fun setUpCourseControllerDtosTest() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(coursesController)
            .setControllerAdvice(RestExceptionHandler())
            .build()
    }

    @Test
    fun `test get a particular course`() {
        mockMvc.perform(
            get("/api/courses/cuchuflito").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test delete a particular course`() {
        mockMvc.perform(
            delete("/api/courses/cuchuflito").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test create a course - incorrect title body`() {
        mockMvc.perform(
            multipart("/api/courses")
                .file(file)
                .param("title", "")
                .param("description", "description")
                .param("category", "category")
                .param("institutionId", "88a014a8-95b5-40d1-9b67-92ad662e3c10")
        ).andExpect(status().isBadRequest)
        .andExpect(
            jsonPath("$.message").
            value("El campo titulo no puede estar vacío")
        )
    }

    @Test
    fun `test create a course - incorrect id body`() {
        mockMvc.perform(
            multipart("/api/courses")
                .file(file)
                .param("title", "title")
                .param("description", "description")
                .param("category", "category")
                .param("institutionId", "cuchuflito")
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("UUID debe ser valido")
//        )
    }

    @Test
    fun `test get course stats`() {
        mockMvc.perform(
            get("/api/courses/cuchuflito/stats").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }
}