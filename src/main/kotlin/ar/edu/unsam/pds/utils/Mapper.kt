package ar.edu.unsam.pds.utils

import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.SubscribeResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.CourseDetailResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.models.Schedule

import java.time.LocalDate

object Mapper {
    fun buildUserDto(user: User): UserResponseDto {
        return UserResponseDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image,
            id = user.id.toString()
        )
    }

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
        return CourseDetailResponseDto(
            id = course.id.toString(),
            title = course.title,
            description = course.description,
            category = course.category,
            image = course.image,
            assignments = course.assignments.map { buildAssignmentDto(it) }.toMutableSet()
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

    fun patchUser(user: User, userDetail: UserResponseDto): User {
        userDetail.name.let { user.name = it }
        userDetail.lastName.let { user.lastName = it }
        userDetail.email.let { user.email = it }
        userDetail.image.let { user.image = it }
        return user
    }

    fun subscribeResponse(idUser: String, idAssignment: String): SubscribeResponseDto {
        return SubscribeResponseDto(idUser, idAssignment, "Suscripción exitosa", LocalDate.now())
    }

    fun unsubscribeResponse(idUser: String, idAssignment: String): SubscribeResponseDto {
        return SubscribeResponseDto(idUser, idAssignment, "Desuscripción exitosa", LocalDate.now())
    }
}