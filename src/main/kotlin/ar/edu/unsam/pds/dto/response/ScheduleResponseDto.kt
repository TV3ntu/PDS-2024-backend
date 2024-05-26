package ar.edu.unsam.pds.dto.response

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

data class ScheduleResponseDto (
    val days: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val recurrenceWeeks: String,
    val listDates: List<String>
)