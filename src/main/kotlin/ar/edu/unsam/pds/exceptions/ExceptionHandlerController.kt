package ar.edu.unsam.pds.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

data class ErrorResponse(val message: String, val detail: String)

@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(InvalidPasswordException::class)
    fun handleInvalidPasswordException(ex: InvalidPasswordException): ResponseEntity<Any> {
        val errorDetails = ex.message?.let { ErrorResponse(it, "La contraseña proporcionada es incorrecta") }
        return ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED)
    }

}