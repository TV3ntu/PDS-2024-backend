package ar.edu.unsam.pds.dto.request

import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class ScheduleRequestDto (

    @field: NotEmpty(message = "Los dias no pueden estar vacios")
    // deuda tecnica aqui. escribir la validacion personalizada
    val days: List<DayOfWeek>,

    @field: NotNull(message = "La hora de inicio no puede ser nula")
    val startTime: LocalTime,

    @field: NotNull(message = "La hora de finalizaciin no puede ser nula")
    val endTime: LocalTime,

    @field: NotNull(message = "La fecha de inicio no puede ser nula")
    @field: FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    val startDate: LocalDate,

    @field: NotNull(message = "La fecha de finalización no puede ser nula")
    @field: FutureOrPresent(message = "La fecha de inicio no puede ser en el pasado")
    val endDate: LocalDate,

    @field: NotNull(message = "Las semanas de recurrencia no pueden ser nulas")
    // deuda tecnica aqui. escribir la validacion personalizada
    val recurrenceWeeks: RecurrenceWeeks
)