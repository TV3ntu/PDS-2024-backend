package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.services.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.hibernate.validator.constraints.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("")
    @Operation(summary = "Get all users")
    fun getAll(): ResponseEntity<List<UserResponseDto>> {
        return ResponseEntity.ok(userService.getUserAll())
    }

    @PostMapping("login")
    fun login(
        @RequestBody @Valid user: LoginForm,
        request: HttpServletRequest
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity.ok(userService.login(user, request))
    }

    @PostMapping("logout")
    fun logout(
        request: HttpServletRequest
    ): ResponseEntity<Map<String, String>> {
        request.logout()
        return ResponseEntity.ok(mapOf("message" to "Se ha deslogeado correctamente."))
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Get user id")
    fun userItem(
        @PathVariable @UUID idUser: String
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity.ok(userService.getUserItem(idUser))
    }

    @PatchMapping("/{idUser}")
    @Operation(summary = "Update a user's details")
    fun updateDetail(
        @PathVariable @UUID idUser: String,
        @RequestBody @Valid user: UserResponseDto
    ): ResponseEntity<UserResponseDto> {
        val originalUser = userService.updateDetail(idUser, user)
        return ResponseEntity.ok().body(originalUser)
    }

    @GetMapping("/{idUser}/courses")
    @Operation(summary = "Get the user's subscribed courses")
    fun getSubscribedCourses(
        @PathVariable @UUID idUser: String
    ): ResponseEntity<List<CourseResponseDto>> {
        return ResponseEntity.ok(userService.getSubscribedCourses(idUser))
    }

    @GetMapping("/{idUser}/subscriptions")
    @Operation(summary = "Get the user's subscriptions")
    fun getSubscriptions(
        @PathVariable @UUID idUser: String
    ): ResponseEntity<List<SubscriptionResponseDto>> {
        return ResponseEntity.ok(userService.getSubscriptions(idUser))
    }
}
