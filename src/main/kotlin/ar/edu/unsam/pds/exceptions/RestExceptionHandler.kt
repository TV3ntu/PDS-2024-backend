package ar.edu.unsam.pds.exceptions

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.URI

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
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
}