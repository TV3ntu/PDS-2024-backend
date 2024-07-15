package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.exceptions.ValidationException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UUIDValidTest {
    private lateinit var uuidValid: UUIDValid

    @BeforeEach
    fun setUp() {
        uuidValid = object : UUIDValid() {}
    }

    @Test
    fun `Try null uuid`() {
        assertThrows<ValidationException> {
            uuidValid.validatedUUID(null)
        }
    }

    @Test
    fun `Try empty uuid`() {
        assertThrows<ValidationException> {
            uuidValid.validatedUUID("")
        }
    }

    @Test
    fun `Try blank uuid`() {
        assertThrows<ValidationException> {
            uuidValid.validatedUUID("     ")
        }
    }

    @Test
    fun `Try uuid length equals to 37`() {
        assertThrows<ValidationException> {
            uuidValid.validatedUUID("abcdefhijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMN")
        }
    }

    @Test
    fun `Try invalid uuid`() {
        assertThrows<ValidationException> {
            uuidValid.validatedUUID("387a967c-99a5-4938-wwww-a2092acc1617")
        }
    }
}