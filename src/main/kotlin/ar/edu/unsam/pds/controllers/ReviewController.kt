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
@RequestMapping("api/reviews")
@CrossOrigin("*")
class ReviewController : UUIDValid() {
    @Autowired private lateinit var reviewService: ReviewService

    @GetMapping("user")
    @Operation(summary = "Get all user reviews")
    fun getAllUserReviews(
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<List<ReviewResponseDto>> {
        return ResponseEntity.ok(reviewService.getAllUserReviews(principal))
    }

    @GetMapping("course/{courseId}")
    @Operation(summary = "Get reviews of all courses")
    fun getReviewsOfAllCourses(
        @PathVariable courseId: String
    ): ResponseEntity<List<ReviewResponseDto>> {
        this.validatedUUID(courseId)
        return ResponseEntity.ok(reviewService.getReviewsOfAllCourses(courseId))
    }

    @PostMapping("")
    @Operation(summary = "Create reviews")
    fun createReviews(
        @RequestBody @Valid review: ReviewRequestDto,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<ReviewResponseDto> {
        return ResponseEntity.ok(reviewService.createReviews(principal, review))
    }
}