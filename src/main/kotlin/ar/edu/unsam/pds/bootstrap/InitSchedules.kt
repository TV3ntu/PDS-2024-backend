package ar.edu.unsam.pds.bootstrap

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
            listOf(DayOfWeek.MONDAY),
            LocalTime.of(19, 0),
            LocalTime.of(20, 0),
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2024, 10, 30),
            1,
        )
        scheduleRepository.create(schedule1)


        val schedule2 = Schedule(
            listOf(DayOfWeek.TUESDAY),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2024, 12, 30),
            2,
        )
        scheduleRepository.create(schedule2)


        val schedule3 = Schedule(
            listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2025, 6, 30),
            3,
        )
        scheduleRepository.create(schedule3)


        val schedule4 = Schedule(
            listOf(DayOfWeek.THURSDAY),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2025, 3, 30),
            4,
        )
        scheduleRepository.create(schedule4)
    }

}