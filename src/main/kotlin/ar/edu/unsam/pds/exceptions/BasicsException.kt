package ar.edu.unsam.pds.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServerError(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class InvalidPasswordException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NotFoundException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.FORBIDDEN)
class PermissionDeniedException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.CONFLICT)
class ValidationException(message: String) : RuntimeException(message)