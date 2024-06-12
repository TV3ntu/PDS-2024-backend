package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.repository.ScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@Component(value = "InitSchedules.beanName")
class InitSchedules : BootstrapGeneric("Schedules") {
    @Autowired private lateinit var scheduleRepository: ScheduleRepository

    override fun doAfterPropertiesSet() {
        val schedule1 = Schedule(
            days = listOf(DayOfWeek.MONDAY),
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(20, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2024, 10, 30),
            recurrenceWeeks = RecurrenceWeeks.WEEKLY,
        )
        scheduleRepository.save(schedule1)


        val schedule2 = Schedule(
            days = listOf(DayOfWeek.TUESDAY),
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(21, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2024, 12, 30),
            recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
        )
        scheduleRepository.save(schedule2)


        val schedule3 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 6, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule3)


        val schedule4 = Schedule(
            days = listOf(DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule4)

        val schedule5 = Schedule(
            days = listOf(DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule5)

        // Create 10 more random schedules

        val schedule6 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule6)

        val schedule7 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )

        scheduleRepository.save(schedule7)

        val schedule8 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule8)

        val schedule9 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule9)

        val schedule10 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule10)

        val schedule11 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule11)

        val schedule12 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule12)

        val schedule13 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule13)

        val schedule14 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule14)

        val schedule15 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule15)

        val schedule16 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule16)

        val schedule17 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule17)

        val schedule18 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule18)

        val schedule19 = Schedule(
            days = listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule19)

        val schedule20 = Schedule(
            days = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
            startTime = LocalTime.of(12, 0),
            endTime = LocalTime.of(14, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2025, 3, 30),
            recurrenceWeeks = RecurrenceWeeks.MONTHLY,
        )
        scheduleRepository.save(schedule20)

    }
}