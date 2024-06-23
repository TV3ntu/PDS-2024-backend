package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.exceptions.ValidationException
import java.util.UUID

abstract class UUIDValid {
    fun validatedUUID(uuid: String?) {
        if (uuid == null) {
            throw ValidationException("El uuid no debe ser nulo")
        }

        if (uuid.isEmpty()) {
            throw ValidationException("El uuid no debe estar vacio")
        }

        if (uuid.isBlank()) {
            throw ValidationException("El uuid no debe estar en blanco")
        }

        if (uuid.length > 36) {
            throw ValidationException("El uuid no debe tener 36caracteres")
        }

        try {
            UUID.fromString(uuid)
        } catch (e: RuntimeException) {
            throw ValidationException("El uuid es invalido")
        }
    }
}