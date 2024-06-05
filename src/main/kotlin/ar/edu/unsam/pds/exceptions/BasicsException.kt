package ar.edu.unsam.pds.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


class BadRequestException(message: String) : RuntimeException(message)

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServerError(message: String) : RuntimeException(message)


class InvalidPasswordException(message: String) : RuntimeException(message)


class NotFoundException(message: String) : RuntimeException(message)


class PermissionDeniedException(message: String) : RuntimeException(message)

class ValidationException(message: String) : RuntimeException(message)