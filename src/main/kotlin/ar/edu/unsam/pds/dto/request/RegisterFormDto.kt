package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class RegisterFormDto(
    @field:NotBlank(message = "El nombre es obligatorio")
    val name: String,

    @field:NotBlank(message = "El apellido es obligatorio")
    val lastName: String,

    @field: NotEmpty(message = "El campo email es requerido")
    @field: NotBlank(message = "El campo email no debe estar en blanco")
    @field: Email(message = "Debe ser una dirección de correo electrónico con formato correcto")
    val email: String,

    @field: NotEmpty(message = "El campo password es requerido")
    @field: NotBlank(message = "El campo password no debe estar en blanco")
    @field: Size(max = 128, message = "El password no debe superar los 128 caracteres")
    val password: String
)