package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.AssignmentStatsResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Institution
import java.time.LocalDate

object AssignmentMapper {

    fun buildAssignmentDto(assignment: Assignment): AssignmentResponseDto {
        return AssignmentResponseDto(
            id = assignment.id.toString(),
            quotas = assignment.quotas,
            quantityAvailable = assignment.quantityAvailable(),
            isActive = assignment.isActive,
            price = assignment.price,
            schedule = ScheduleMapper.buildScheduleDto(assignment.schedule)
        )
    }

    fun subscribeResponse(idUser: String, idAssignment: String): SubscribeResponseDto {
        return SubscribeResponseDto(idUser, idAssignment, "Suscripción exitosa", LocalDate.now())
    }

    fun unsubscribeResponse(idUser: String, idAssignment: String): SubscribeResponseDto {
        return SubscribeResponseDto(idUser, idAssignment, "Desuscripción exitosa", LocalDate.now())
    }

    fun buildAssignmentStatsDto(assignment: Assignment): AssignmentStatsResponseDto {
        return AssignmentStatsResponseDto(
            id = assignment.id.toString(),
            quotas = assignment.quotas,
            quantityAvailable = assignment.quantityAvailable(),
            isActive = assignment.isActive,
            price = assignment.price,
            schedule = ScheduleMapper.buildScheduleDto(assignment.schedule),
            name = assignment.name(),
            subscribers = assignment.subscribedUsers.map { UserMapper.buildUserDto(it) }.toMutableSet(),
            totalIncome = assignment.totalIncome(),
            status = assignment.status()
        )
    }

    fun buildSubscriptionDto(assignment: Assignment, institution: Institution
    ): SubscriptionResponseDto {
        return SubscriptionResponseDto(
            assignmentId = assignment.id.toString(),
            institutionName = institution.name,
            courseId = assignment.course.id.toString(),
            courseName = assignment.course.title,
            date = assignment.schedule.nextDate().toString(),
            hour = assignment.schedule.startTime.toString(),
            status = assignment.status()
        )
    }
}