package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.security.models.Principal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface AssignmentRepository : JpaRepository<Assignment, UUID>{


    @Query("""
        SELECT COUNT(course.id) = 1
            FROM Institution i
            JOIN i.courses course
            JOIN course.assignments assignments
            JOIN i.admin admins
            WHERE assignments.id = :idAssignment AND admins.id = :#{#principal.user.id}
            """)

    fun isOwner(@Param("idAssignment") idCourse: UUID, @Param("principal") principal: Principal) : Boolean

}