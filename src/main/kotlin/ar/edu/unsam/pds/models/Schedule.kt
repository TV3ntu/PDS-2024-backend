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
        return days.flatMap { day ->
            generateSequence(startDate.with(TemporalAdjusters.nextOrSame(day))) { currentDate ->
                currentDate.plusWeeks(recurrenceWeeks.value)
                    .takeIf { it.isBefore(endDate) || it.isEqual(endDate) }
            }.map { it.toString() }.toList()
        }
    }
}

enum class RecurrenceWeeks(val value: Long) {
    WEEKLY(1),
    BIWEEKLY(2),
    MONTHLY(4)
}


