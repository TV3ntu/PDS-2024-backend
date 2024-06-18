package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.security.models.Principal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface CourseRepository : JpaRepository<Course, UUID> {
    @Query(
        """
        SELECT c FROM Course c
        WHERE c.title LIKE concat('%', :query, '%')
        OR c.description LIKE concat('%', :query, '%')
        OR c.category LIKE concat('%', :query, '%')
        ORDER BY 
        CASE 
            WHEN c.title LIKE concat('%', :query, '%') THEN 1
            WHEN c.category LIKE concat('%', :query, '%') THEN 2
            ELSE 3
        END
    """
    )
    fun getAllBy(@Param("query") query: String): MutableList<Course>

    @Query(
        """
        SELECT COUNT(cursos.id) = 1
            FROM Institution i
            JOIN i.courses cursos
            JOIN i.admin admins
            WHERE cursos.id = :idCourse AND admins.id = :#{#principal.user.id}
            """
    )
    fun isOwner(@Param("idCourse") idCourse: UUID, @Param("principal") principal: Principal): Boolean

    @Query(
        """
        SELECT c FROM Course c 
            JOIN c.assignments assigments
            WHERE assigments.id = :idAssigment
            """
    )
    fun findByAssigmentId(@Param("idAssigment") id: UUID): Optional<Course>

}