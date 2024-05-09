package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/")
    @Operation(summary = "Get all users")
    fun getAll(): ResponseEntity<List<UserResponseDto>> {
        return ResponseEntity.ok(userService.getUserAll())
    }

    @PostMapping("login")
    fun login(
        @RequestBody user: LoginForm,
        request: HttpServletRequest
    ): ResponseEntity<UserResponseDto> {
        try {
            request.login(user.email, user.password)
        } catch (e: ServletException) {
            throw NotFoundException("Usuario y/o contraseña invalidos.")
        }

        val principal = (request.userPrincipal as Authentication).principal as Principal
        val principalUser = principal.user ?: throw InternalServerError("Internal Server Error")

        return ResponseEntity.ok(UserResponseDto(
            principalUser.name,
            principalUser.lastName,
            principalUser.email,
            principalUser.image
        ))
    }

    @PostMapping("logout")
    fun logout(
        request: HttpServletRequest
    ): ResponseEntity<String> {
        request.logout()
        return ResponseEntity.ok("{\"message\": \"Se ha deslogeado correctamente.\"}")
    }
}
