package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.repository.Element
import java.time.LocalDate
import java.time.LocalTime
import java.time.DayOfWeek
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

class Schedule(
    val days: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val recurrenceWeeks: Long
) : Element
{
    val id: String = java.util.UUID.randomUUID().toString()
    override fun findMe(value: String): Boolean = id == value
    fun generateSchedule(): List<String> {
        val schedule = mutableListOf<String>()

        // Para cada día de la semana en la lista de días
        for (day in days) {
            // Ajustar la fecha de inicio al próximo o mismo día de la semana especificado
            var currentDate = startDate.with(TemporalAdjusters.nextOrSame(day))

            // Mientras la fecha actual esté antes o igual que la fecha de fin
            while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
                // Añadir la fecha y los tiempos al horario
                schedule.add(currentDate.toString())
                // Incrementar la fecha actual por el número de semanas especificado
                currentDate = currentDate.plus(recurrenceWeeks, ChronoUnit.WEEKS)
            }
        }
        return schedule
    }
}
