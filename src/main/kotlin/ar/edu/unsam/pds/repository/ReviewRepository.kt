package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Review
import ar.edu.unsam.pds.security.models.Principal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface ReviewRepository : JpaRepository<Review, UUID> {
    @Query("""
        SELECT r FROM Review r WHERE r.user.id = :#{#principal.user.id}
    """)
    fun getAllUserReviews(@Param("principal") principal: Principal): List<Review>

    @Query("""
        SELECT r FROM Review r WHERE r.course.id = :courseId
    """)
    fun getReviewsOfAllCourses(@Param("courseId") courseId: UUID): List<Review>

    @Query("""
        SELECT COUNT(r) = 1 FROM Review r
        WHERE r.course.id = :#{#course.id} AND r.user.id = :#{#principal.user.id}
    """)
    fun existsReviewBy(@Param("course") course: Course, @Param("principal") principal: Principal): Boolean
}