package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.repository.Element
import java.time.LocalDate
import java.time.LocalTime
import java.time.DayOfWeek
import java.time.temporal.TemporalAdjusters

class Schedule(
    val days: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val recurrenceWeeks: RecurrenceWeeks
) : Element
{
    val id: String = java.util.UUID.randomUUID().toString()
    override fun findMe(value: String): Boolean = id == value
    fun generateSchedule(): List<String> {
        val schedule = mutableListOf<String>()

        for (day in days) {
            // Ajustar la fecha de inicio al próximo o mismo día de la semana especificado
            var currentDate = startDate.with(TemporalAdjusters.nextOrSame(day))

            while (!currentDate.isAfter(endDate)) {
                schedule.add(currentDate.toString())
                // Incrementar la fecha actual por el número de semanas especificado
                currentDate = currentDate.plusWeeks(recurrenceWeeks.value)
            }
        }
        return schedule
    }
}

enum class RecurrenceWeeks(val value: Long) {
    WEEKLY(1),
    BIWEEKLY(2),
    MONTHLY(4)
}


