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
    }
}