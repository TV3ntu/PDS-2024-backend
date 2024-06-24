package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.ReviewRequestDto
import ar.edu.unsam.pds.dto.response.ReviewResponseDto
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.ReviewService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("") @CrossOrigin("*")
class ReviewController : UUIDValid() {
    @Autowired lateinit var reviewService: ReviewService

    @GetMapping("api/users/reviews")
    @Operation(summary = "Get all user reviews")
    fun getAllUserReviews(
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<List<ReviewResponseDto>> {
        return ResponseEntity.ok(reviewService.getAllUserReviews(principal))
    }

    @GetMapping("api/courses/{courseId}/reviews")
    @Operation(summary = "Get reviews of all courses")
    fun getReviewsOfAllCourses(
        @PathVariable courseId: String
    ): ResponseEntity<List<ReviewResponseDto>> {
        this.validatedUUID(courseId)
        return ResponseEntity.ok(reviewService.getReviewsOfAllCourses(courseId))
    }

    @PostMapping("api/courses/{courseId}/review")
    @Operation(summary = "Create reviews")
    fun createReviews(
        @PathVariable courseId: String,
        @RequestBody @Valid review: ReviewRequestDto,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<ReviewResponseDto> {
        this.validatedUUID(courseId)
        return ResponseEntity.ok(reviewService.createReviews(principal, courseId, review))
    }
}