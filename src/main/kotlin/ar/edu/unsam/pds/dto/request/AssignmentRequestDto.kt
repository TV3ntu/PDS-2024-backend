package ar.edu.unsam.pds.dto.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class AssignmentRequestDto(
    @field: NotNull(message = "Course ID cannot be null")
    @field: NotBlank(message = "Course ID cannot be null")
    @field: Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "Course ID must be a valid UUID"
    )
    val idCourse: String,
    @field: Min(message = "Minimo deberia ser 1 clase", value=1)
    @field: Max(message = "Maximo deberia haber 10000 clases", value=10000)
    @field: NotNull(message = "Course ID cannot be null")
    val quotas: Int,
    @field: Min(message = "Minimo deberia ser 1 peso", value=1)
    @field: Max(message = "Maximo deberia haber 100000000 pesos", value=100000000)
    @field: NotNull(message = "Course ID cannot be null")
    val price: Double,
    val schedule: ScheduleRequestDto
)