package ar.edu.unsam.pds.exceptions.controllers

import org.junit.jupiter.api.BeforeEach
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc

open class ControllersDtosBasicTest {
    lateinit var mockMvc: MockMvc
    lateinit var file: MockMultipartFile

    @BeforeEach
    fun setUp() {
        file = MockMultipartFile(
            "file",
            "filename.jpg",
            "image/jpeg",
            "some content".toByteArray()
        )
    }
}