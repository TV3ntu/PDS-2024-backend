package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class LoginForm(
    @field: NotEmpty @field: NotBlank @field: Email
    val email: String,

    @field: NotEmpty @field: NotBlank @field: Size(max = 128)
    val password: String
)