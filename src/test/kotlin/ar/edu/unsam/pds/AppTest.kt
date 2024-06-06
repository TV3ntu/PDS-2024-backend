package ar.edu.unsam.pds

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(classes = [ProjectApplication::class], webEnvironment = RANDOM_PORT)
class AppTest {
    @Autowired
    private lateinit var context: WebApplicationContext
    private lateinit var mvc: MockMvc

    private val host: String = "http://localhost:8080/"

    //    protected LocalDateTime dateToday = LocalDateTime.now();
    @BeforeEach
    fun setup() {
        this.mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
            .build()
    }

    fun getMvc(url: String): MockHttpServletResponse {
        val response = mvc.perform(
            MockMvcRequestBuilders.get(host + url)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        response.characterEncoding = "UTF-8"

        return response
    }

    @Test
    fun `test app`() {
        val response = getMvc("/")
        assertEquals(response.status, 403)
    }
}