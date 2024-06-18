package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
import ar.edu.unsam.pds.models.Schedule

object ScheduleMapper {

    fun buildScheduleDto(schedule: Schedule): ScheduleResponseDto {
        return ScheduleResponseDto(
            days = schedule.days,
            startTime = schedule.startTime,
            endTime = schedule.endTime,
            startDate = schedule.startDate,
            endDate = schedule.endDate,
            recurrenceWeeks = schedule.recurrenceWeeks.name,
            listDates = schedule.generateSchedule()
        )
    }
}