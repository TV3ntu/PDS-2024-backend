package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Assignment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AssignmentRepository : JpaRepository<Assignment, UUID>
