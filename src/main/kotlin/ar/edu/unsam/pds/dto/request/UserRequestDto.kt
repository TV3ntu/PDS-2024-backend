package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.*

data class UserRequestDto(
    @field: NotEmpty(message = "El campo name es requerido")
    @field: NotBlank(message = "El campo name no debe estar en blanco")
    @field: Size(max = 128, message = "El name no debe superar los 128 caracteres")
    val name: String,

    @field: NotEmpty(message = "El campo lastName es requerido")
    @field: NotBlank(message = "El campo lastName no debe estar en blanco")
    @field: Size(max = 128, message = "El lastName no debe superar los 128 caracteres")
    val lastName: String,


    @field: NotEmpty(message = "El campo email es requerido")
    @field: NotBlank(message = "El campo email no debe estar en blanco")
    @field: Email(message = "Debe ser una dirección de correo electrónico con formato correcto")
    val email: String,

    // máximo de internet explorer, por compatibilidad, el más largo del más corto
    @field: Size(max = 2083, message = "El image no debe superar los 2083 caracteres")
    val image: String,

    @field:NotNull
    val isAdmin: Boolean,

    @field:Positive(message = "Los créditos deben ser un número positivo o cero")
    val credits: Double,

    @field: NotNull(message = "Course ID cannot be null")
    @field: NotBlank(message = "Course ID cannot be null")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Course ID must be a valid UUID"
    )
    val id: String,

    val nextClass: String?
)