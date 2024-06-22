package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class AssignmentRequestDto(
    @field: NotNull(message = "El ID no debe ser nulo")
    @field: NotBlank(message = "El ID no debe estar en blanco")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "UUID debe ser valido"
    )
    val idCourse: String?,

    @field: Min(message = "Minimo deberia ser 1 clase", value=1)
    @field: Max(message = "Maximo deberia haber 10000 clases", value=10000)
    @field: NotNull(message = "El campo clases no puede ser nulo")
    val quotas: Int = 0,

    @field: Min(message = "Minimo deberia ser 1 peso", value=1)
    @field: Max(message = "Maximo deberia haber 100000000 pesos", value=100000000)
    @field: NotNull(message = "Elcampo precio o pued ser nulo")
    val price: Double = 0.0,

    val schedule: ScheduleRequestDto
)