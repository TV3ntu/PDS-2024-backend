package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.services.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/")
    @Operation(summary = "Get all users")
    fun userAll(): ResponseEntity<List<UserResponseDto>> {
        return ResponseEntity.ok(userService.getUserAll())
    }

}