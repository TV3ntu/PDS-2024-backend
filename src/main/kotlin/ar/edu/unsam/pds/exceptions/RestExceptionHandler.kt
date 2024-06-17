package ar.edu.unsam.pds.exceptions

import ar.edu.unsam.pds.dto.exceptions.BodyResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun customHandleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<BodyResponse> {
        val status = HttpStatus.BAD_REQUEST
        val message = exception.bindingResult.fieldErrors[0].defaultMessage
        val body = BodyResponse(status, request, message)
        return ResponseEntity(body, status)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun customHandleHttpMessageNotReadable(
        exception: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<BodyResponse> {
        val status = HttpStatus.BAD_REQUEST
        val message = "Mensaje http no legible"
        val body = BodyResponse(status, request, message)
        return ResponseEntity(body, status)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [UsernameNotFoundException::class])
    fun handleUsernameNotFoundException(
        exception: UsernameNotFoundException,
        request: WebRequest
    ): ResponseEntity<BodyResponse> {
        val status = HttpStatus.BAD_REQUEST
        val body = BodyResponse(status, request, exception.message)
        return ResponseEntity(body, status)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(exception: NotFoundException, request: WebRequest): BodyResponse{
        return BodyResponse(HttpStatus.NOT_FOUND, request, exception.message)
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = [ValidationException::class])
    fun validation(exception: ValidationException, request: WebRequest): BodyResponse{
        return BodyResponse(HttpStatus.CONFLICT, request, exception.message)
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = [PermissionDeniedException::class])
    fun denied(exception: PermissionDeniedException, request: WebRequest): BodyResponse{
        return BodyResponse(HttpStatus.FORBIDDEN, request, exception.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun customHandleIllegalArgumentException(
        exception: IllegalArgumentException,
        request: WebRequest
    ): ResponseEntity<BodyResponse> {
        val status = HttpStatus.BAD_REQUEST
        val message = "Mensaje http no legible"
        val body = BodyResponse(status, request, message)
        return ResponseEntity(body, status)
    }

}
