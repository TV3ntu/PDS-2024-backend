package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.*

data class ReviewRequestDto(
    @field: NotNull(message = "El ID no debe ser nulo")
    @field: NotBlank(message = "El ID no debe estar en blanco")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "UUID debe ser valido"
    )
    val courseId: String?,

    @field: Min(message = "Minimo deberia ser 0", value = 0)
    @field: Max(message = "Maximo deberia ser 5", value = 5)
    @field: NotNull(message = "El campo clases no puede ser nulo")
    val rating: Int = -1,

    @field: NotEmpty(message = "El campo description es requerido")
    @field: NotBlank(message = "El campo description no debe estar en blanco")
    @field: Size(max = 128, message = "El description no debe superar los 128 caracteres")
    val description: String = ""
)