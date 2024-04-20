package ar.edu.unsam.pds.exceptions
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BadRequestExceptionTest {

    @Test
    fun `test BadRequestException message`() {
        val message = "Bad request"
        val exception = BadRequestException(message)
        Assertions.assertEquals(message, exception.message)

    }
}