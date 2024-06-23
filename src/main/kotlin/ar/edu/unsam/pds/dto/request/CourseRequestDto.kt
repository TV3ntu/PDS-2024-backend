package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.web.multipart.MultipartFile

data class CourseRequestDto(
    @field: NotBlank(message = "El campo titulo no puede estar vacío")
    @field: NotNull(message = "El campo titulo no puede ser nulo")
    @field: Size(max = 250, message = "El campo titulo debe tener como máximo 250 caracteres")
    val title: String = "",

    @field: NotBlank(message = "El campo descripcion no puede estar vacío")
    @field: NotNull(message = "El campo descripcion no puede ser nulo")
    @field: Size(max = 250, message = "El campo descripcion debe tener como máximo 250 caracteres")
    val description: String = "",

    @field: NotBlank(message = "El campo categoria no puede estar vacío")
    @field: NotNull(message = "El campo categoria no puede ser nulo")
    @field: Size(max = 250, message = "El campo categoria debe tener como máximo 250 caracteres")
    var category: String = "",

    @field: NotNull(message = "El archivo no puede estar vacio")
    val file: MultipartFile,

    @field: NotNull(message = "El ID no debe ser nulo")
    @field: NotBlank(message = "El ID no debe estar en blanco")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "UUID debe ser valido"
    )
    var institutionId: String?
)