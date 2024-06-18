package ar.edu.unsam.pds.utils

import ar.edu.unsam.pds.dto.response.*
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.models.Schedule

import java.time.LocalDate

object Mapper {

    fun buildInstitutionDto(institution: Institution): InstitutionResponseDto {
        return InstitutionResponseDto(
            id = institution.id.toString(),
            name = institution.name,
            description = institution.description,
            category = institution.category,
            image = institution.image
        )
    }

    fun buildInstitutionDetailDto(institution: Institution): InstitutionDetailResponseDto {
        return InstitutionDetailResponseDto(
            id = institution.id.toString(),
            name = institution.name,
            description = institution.description,
            category = institution.category,
            image = institution.image,
            courses = institution.courses.map { buildCourseDto(it) }.toMutableSet()
        )
    }

    fun buildCourseDto(course: Course): CourseResponseDto {
        return CourseResponseDto(
            id = course.id.toString(),
            title = course.title,
            description = course.description,
            category = course.category,
            image = course.image
        )
    }

    fun buildCourseDetailDto(course: Course): CourseDetailResponseDto {
        val assignments = if(course.assignments.isEmpty()) mutableSetOf<AssignmentResponseDto>() else course.assignments.map { buildAssignmentDto(it) }.toMutableSet()

        return CourseDetailResponseDto(
            id = course.id.toString(),
            title = course.title,
            description = course.description,
            category = course.category,
            image = course.image,
            assignments = assignments
        )
    }

    fun buildAssignmentDto(assignment: Assignment): AssignmentResponseDto {
        return AssignmentResponseDto(
            id = assignment.id.toString(),
            quotas = assignment.quotas,
            quantityAvailable = assignment.quantityAvailable(),
            isActive = assignment.isActive,
            price = assignment.price,
            schedule = buildScheduleDto(assignment.schedule)
        )
    }

    private fun buildScheduleDto(schedule: Schedule): ScheduleResponseDto {
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

    fun subscribeResponse(idUser: String, idAssignment: String): SubscribeResponseDto {
        return SubscribeResponseDto(idUser, idAssignment, "Suscripción exitosa", LocalDate.now())
    }

    fun unsubscribeResponse(idUser: String, idAssignment: String): SubscribeResponseDto {
        return SubscribeResponseDto(idUser, idAssignment, "Desuscripción exitosa", LocalDate.now())
    }

    fun buildCourseStatsDto(course: Course): CourseStatsResponseDto {

        val totalAssignments = if (course.assignments.isEmpty()) 0 else course.assignments.size
        val totalSubscriptions = if (course.assignments.isEmpty()) 0 else course.totalSubscribedUsers()
        val totalIncome = if (course.assignments.isEmpty()) 0.0 else course.totalIncome()
        val mostPopularAssignment = if (course.assignments.isEmpty()) null else buildAssignmentStatsDto(course.mostPopularAssignment())
        val mostProfitableAssignment = if (course.assignments.isEmpty()) null else buildAssignmentStatsDto(course.mostProfitableAssignment())
        val assignments = if (course.assignments.isEmpty()) mutableSetOf<AssignmentStatsResponseDto>() else course.assignments.map { buildAssignmentStatsDto(it) }.toMutableSet()

        return CourseStatsResponseDto(
                id = course.id.toString(),
                title = course.title,
                description = course.description,
                category = course.category,
                image = course.image,
                totalAssignments = totalAssignments,
                totalSubscriptions = totalSubscriptions,
                totalIncome = totalIncome,
                mostPopularAssignment = mostPopularAssignment,
                mostProfitableAssignment = mostProfitableAssignment,
                assignments = assignments
        )
    }

    fun buildAssignmentStatsDto(assignment: Assignment): AssignmentStatsResponseDto {
        return AssignmentStatsResponseDto(
            id = assignment.id.toString(),
            quotas = assignment.quotas,
            quantityAvailable = assignment.quantityAvailable(),
            isActive = assignment.isActive,
            price = assignment.price,
            schedule = buildScheduleDto(assignment.schedule),
            name = assignment.name(),
            subscribers = assignment.subscribedUsers.map { UserMapper.buildUserDto(it) }.toMutableSet(),
            totalIncome = assignment.totalIncome(),
            status = assignment.status()
        )

    }
}