package ar.edu.unsam.pds.exceptions

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.URI

class BadRequestException(message: String) : RuntimeException(message)
class InternalServerError(message: String) : RuntimeException(message)
class InvalidPasswordException(message: String) : RuntimeException(message)
class NotFoundException(message: String) : RuntimeException(message)
class PermissionDeniedException(message: String) : RuntimeException(message)
class ValidationException(message: String) : RuntimeException(message)

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors: MutableMap<String, String> = mutableMapOf()
        val httpStatus = HttpStatus.NOT_FOUND

        exception.bindingResult.fieldErrors.forEach {
            errors[it.field] = it.defaultMessage!!.toString()
        }

        return ResponseEntity.status(httpStatus).body(
            ProblemDetail.forStatus(httpStatus).apply {
                type = URI.create("")
                title = "constraint violation exception"
                detail = ObjectMapper().writeValueAsString(errors).toString()
            }
        )
    }

    @ExceptionHandler(value = [UsernameNotFoundException::class])
    fun handleUsernameNotFoundException(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message, exception)
    }

    @ExceptionHandler(value = [BadRequestException::class])
    fun handleBadRequestException(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message, exception)
    }

    @ExceptionHandler(value = [InternalServerError::class])
    fun handleInternalServerError(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.message, exception)
    }

    @ExceptionHandler(value = [InvalidPasswordException::class])
    fun handleInvalidPasswordException(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.message, exception)
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun handleNotFoundException(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.NOT_FOUND, exception.message, exception)
    }

    @ExceptionHandler(value = [PermissionDeniedException::class])
    fun handlePermissionDeniedException(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.FORBIDDEN, exception.message, exception)
    }

    @ExceptionHandler(value = [ValidationException::class])
    fun handleValidationException(exception: RuntimeException, request: WebRequest): ResponseStatusException {
        return ResponseStatusException(HttpStatus.CONFLICT, exception.message, exception)
    }
}