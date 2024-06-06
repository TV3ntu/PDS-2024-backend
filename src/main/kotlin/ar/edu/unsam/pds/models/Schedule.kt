package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.util.*

@Entity @Table(name = "APP_SCHEDULE")
@EntityListeners(AuditingEntityListener::class)
class Schedule(
    val days: List<DayOfWeek>,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val recurrenceWeeks: RecurrenceWeeks
) : Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @CreatedDate
    @Column(name = "REGISTER_DATE", nullable = false, updatable = false)
    lateinit var registerDate: LocalDateTime

    @LastModifiedDate
    @Column(name = "LAST_UPDATE", nullable = false)
    lateinit var lastUpdate: LocalDateTime

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