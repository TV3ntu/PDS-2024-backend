package ar.edu.unsam.pds.exceptions

import ar.edu.unsam.pds.dto.exceptions.BodyResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = exception.bindingResult.fieldErrors.associate {
            it.field to it.defaultMessage!!
        }

        return ResponseEntity(errors, headers, status)
    }

    override fun handleHttpMessageNotReadable(
        exception: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val message = "Mensaje http no legible"
        val body = BodyResponse(status, request, message)
        return ResponseEntity(body, headers, status)
    }

    @ExceptionHandler(value = [UsernameNotFoundException::class])
    fun handleUsernameNotFoundException(
        exception: UsernameNotFoundException,
        request: WebRequest
    ): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val body = BodyResponse(status, request, exception.message)
        return ResponseEntity(body, HttpHeaders(), status)
    }
}
