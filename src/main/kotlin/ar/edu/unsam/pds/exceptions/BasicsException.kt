package ar.edu.unsam.pds.exceptions

class BadRequestException(message: String) : RuntimeException(message)

class InternalServerError(message: String) : RuntimeException(message)

class InvalidPasswordException(message: String) : RuntimeException(message)

class NotFoundException(message: String) : RuntimeException(message)

class PermissionDeniedException(message: String) : RuntimeException(message)

class ValidationException(message: String) : RuntimeException(message)