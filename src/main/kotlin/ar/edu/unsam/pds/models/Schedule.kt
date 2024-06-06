package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import jakarta.persistence.*
import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.util.*

@Entity @Table(name = "APP_SCHEDULE")
class Schedule(
    val days: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val recurrenceWeeks: RecurrenceWeeks
) : Timestamp(), Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    fun generateCompleteSchedule(): List<String> {
        return days.flatMap { this.rangeOfDays(it) }
    }

    fun generateSchedule(): List<String> {
        val today = LocalDate.now()
        return days.flatMap { this.rangeOfDays(it) }
            .filter { it >= today.toString() }
    }

    private fun rangeOfDays(day: DayOfWeek): Sequence<String> {
        val initialDate = startDate.with(TemporalAdjusters.nextOrSame(day))

        return generateSequence(initialDate) { date ->
            date.plusWeeks(recurrenceWeeks.value)
                .takeIf { it.isBefore(endDate) || it.isEqual(endDate) }
        }.map { it.toString() }
    }

    fun isBeforeEndDate(date: LocalDate): Boolean {
        return date.isBefore(endDate) || date.isEqual(endDate)
    }

    fun nextDate(): LocalDate? {
        val today = LocalDate.now()
        val scheduleDates = generateSchedule()
            .map { LocalDate.parse(it) }
            .filter { it.isAfter(today) || it.isEqual(today) }

        return scheduleDates.minOrNull()
    }
}