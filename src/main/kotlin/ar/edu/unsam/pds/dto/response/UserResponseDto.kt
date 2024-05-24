package ar.edu.unsam.pds.dto.response

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.UUID

data class UserResponseDto(
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

    @field: Size(max = 128, message = "El image no debe superar los 128 caracteres")
    val image: String,

    @field: UUID(message = "Debe ser un UUID válido")
    val id: String
)