package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Schedule
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ScheduleRepository : JpaRepository<Schedule, UUID>