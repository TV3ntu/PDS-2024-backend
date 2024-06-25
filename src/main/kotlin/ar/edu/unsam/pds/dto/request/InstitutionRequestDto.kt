package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.web.multipart.MultipartFile

data class InstitutionRequestDto(
    @field: NotBlank(message = "El nombre no puede estar vacío")
    @field: NotNull(message = "El nombre no puede ser nulo")
    @field: Size(max = 250, message = "El nombre debe tener como máximo 250 caracteres")
    val name: String = "",

    @field: NotBlank(message = "La descripción no puede estar vacía")
    @field: NotNull(message = "El descripción no puede ser nulo")
    @field: Size(max = 250, message = "El nombre debe tener como máximo 250 caracteres")
    val description: String = "",

    @field: NotBlank(message = "La categoría no puede estar vacía")
    @field: NotNull(message = "El categoría no puede ser nulo")
    @field: Size(max = 250, message = "El nombre debe tener como máximo 250 caracteres")
    val category: String = "",

    @field: NotNull(message = "El archivo no puede estar vacio")
    val file: MultipartFile
)