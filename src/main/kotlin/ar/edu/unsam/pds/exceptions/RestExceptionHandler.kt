package ar.edu.unsam.pds.exceptions

import jakarta.servlet.http.HttpServletResponse
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
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors: MutableMap<String, String> = mutableMapOf()

        exception.bindingResult.fieldErrors.forEach {
            errors[it.field] = it.defaultMessage!!.toString()
        }

        return buildResponse(status, headers, errors, request)
    }

    override fun handleHttpMessageNotReadable(
        exception: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val message = retouchMessage(exception.message, request)

        return buildResponse(status, headers, message, request)
    }

    @ExceptionHandler(value = [UsernameNotFoundException::class])
    fun handleUsernameNotFoundException(response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value())
    }

    private fun buildResponse(
        status: HttpStatusCode,
        headers: HttpHeaders,
        message: Any,
        request: WebRequest
    ): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = mutableMapOf()

        body["timestamp"] = LocalDateTime.now()
        body["status"] = status.value()
        body["error"] = HttpStatus.valueOf(status.value()).name
        body["message"] = message
        body["path"] = request.getDescription(false).replace("uri=", "")

        return ResponseEntity(body, headers, status)
    }

    private fun retouchMessage(
        message: String?,
        request: WebRequest
    ): String {
        val className = message?.substringAfter("[")?.substringBefore("]") ?: ""
        val url = request.getDescription(false).replace("uri=", "")

        return message?.replace(className, url) ?: ""
    }
}