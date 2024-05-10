package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class LoginForm(
    @field: NotEmpty(message = "El campo email no debe estar vacio")
    @field: NotBlank(message = "El campo email no debe estar en blanco")
    @field: Email
    val email: String,

    @field: NotEmpty(message = "El campo password no debe estar vacio")
    @field: NotBlank(message = "El campo password no debe estar en blanco")
    @field: Size(max = 128, message = "el password no debe superar los 128 caracteres")
    val password: String
)