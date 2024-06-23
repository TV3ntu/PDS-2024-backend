package ar.edu.unsam.pds.exceptions.controllers

import ar.edu.unsam.pds.controllers.UserController
import ar.edu.unsam.pds.exceptions.RestExceptionHandler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class UserControllerDtosTest {
    private lateinit var mockMvc: MockMvc

    @InjectMocks
    private lateinit var userController: UserController

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .setControllerAdvice(RestExceptionHandler())
            .build()
    }

    @Test
    fun `test register a particular user - empty name field`() {
        mockMvc.perform(
            post("/api/users/register").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "name": "",
                    "lastName": "perez",
                    "email": "juan_perez@email.com",
                    "password": "666"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El campo nombre no puede estar vacío")
//        )
    }

    @Test
    fun `test register a particular user - empty lastName field`() {
        mockMvc.perform(
            post("/api/users/register").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "name": "juan",
                    "lastName": "",
                    "email": "juan_perez@email.com",
                    "password": "666"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El campo apellido no puede estar vacío")
//        )
    }

    @Test
    fun `test register a particular user - incorrect email field`() {
        mockMvc.perform(
            post("/api/users/register").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "name": "juan",
                    "lastName": "perez",
                    "email": "juan_perez",
                    "password": "666"
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("Debe ser una dirección de correo electrónico con formato correcto")
//        )
    }

    @Test
    fun `test register a particular user - empty password field`() {
        mockMvc.perform(
            post("/api/users/register").
            contentType(APPLICATION_JSON).
            content("""
                {
                    "name": "juan",
                    "lastName": "perez",
                    "email": "juan_perez@email.com",
                    "password": ""
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El campo contraseña no puede estar vacío")
//        )
    }

    @Test
    fun `test get a particular user`() {
        mockMvc.perform(
            get("/api/users/cuchuflito").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test update a particular user - incorrect path id`() {
        mockMvc.perform(
            patch("/api/users/cuchuflito").
            contentType(APPLICATION_JSON).
            content("""
                {
                  "id": "88a014a8-95b5-40d1-9b67-92ad662e3c10",
                  "name": "Adan1",
                  "lastName": "AdanAdan",
                  "email": "adan@email.com",
                  "image": "",
                  "credits": 10000
                }
            """.trimIndent())
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test update a particular user - incorrect body id`() {
        mockMvc.perform(
            patch("/api/users/88a014a8-95b5-40d1-9b67-92ad662e3c10").
            contentType(APPLICATION_JSON).
            content("""
                {
                  "id": "cuchuflito",
                  "name": "Adan1",
                  "lastName": "AdanAdan",
                  "email": "adan@email.com",
                  "image": "",
                  "credits": 10000
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("UUID debe ser valido")
//        )
    }

    @Test
    fun `test update a particular user - incorrect body name`() {
        mockMvc.perform(
            patch("/api/users/88a014a8-95b5-40d1-9b67-92ad662e3c10").
            contentType(APPLICATION_JSON).
            content("""
                {
                  "id": "88a014a8-95b5-40d1-9b67-92ad662e3c10",
                  "name": "",
                  "lastName": "AdanAdan",
                  "email": "adan@email.com",
                  "image": "",
                  "credits": 10000
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El campo nombre no debe estar en blanco")
//        )
    }

    @Test
    fun `test update a particular user - incorrect body lastName`() {
        mockMvc.perform(
            patch("/api/users/88a014a8-95b5-40d1-9b67-92ad662e3c10").
            contentType(APPLICATION_JSON).
            content("""
                {
                  "id": "88a014a8-95b5-40d1-9b67-92ad662e3c10",
                  "name": "Adan",
                  "lastName": "",
                  "email": "adan@email.com",
                  "image": "",
                  "credits": 10000
                }
            """.trimIndent())
        ).andExpect(status().isBadRequest)
//        .andExpect(
//            jsonPath("$.message").
//            value("El campo apellido no debe estar en blanco")
//        )
    }

    @Test
    fun `test get subscribed courses - incorrect path id`() {
        mockMvc.perform(
            get("/api/users/cuchuflito/courses").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }

    @Test
    fun `test get subscribed`() {
        mockMvc.perform(
            get("/api/users/cuchuflito/subscriptions").
            contentType(APPLICATION_JSON)
        ).andExpect(status().isConflict)
//        .andExpect(
//            jsonPath("$.message").
//            value("El uuid es invalido")
//        )
    }
}