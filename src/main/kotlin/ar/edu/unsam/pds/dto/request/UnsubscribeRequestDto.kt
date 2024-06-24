package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class UnsubscribeRequestDto(
    @field: NotNull(message = "El ID de usuario no debe ser nulo")
    @field: NotBlank(message = "El ID de usuario no debe estar en blanco")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "UUID de usuario debe ser valido"
    )
    val idUser: String,

    @field: NotNull(message = "El ID de clase no debe ser nulo")
    @field: NotBlank(message = "El ID de clase no debe estar en blanco")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "UUID de clase debe ser valido"
    )
    val idAssignment: String,
)