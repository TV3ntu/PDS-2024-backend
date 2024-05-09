package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.UserDetailDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.services.UserService
import io.swagger.v3.oas.annotations.Operation
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

    @GetMapping("/")
    @Operation(summary = "Get all users")
    fun userAll(): ResponseEntity<List<UserResponseDto>> {
        return ResponseEntity.ok(userService.getUserAll())
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Get user id")
    fun userItem(
        @PathVariable @UUID idUser: String
    ): ResponseEntity<UserResponseDto> {
        return ResponseEntity.ok(userService.getUserItem(idUser))
    }

    @GetMapping("/{idUser}/detail")
    @Operation(summary = "Obtiene el detalle de un usuario")
    fun getDetail(
        @PathVariable @UUID idUser: String
    ): ResponseEntity<UserDetailDto> {
        val user = userService.getDetail(idUser)
        return ResponseEntity.ok().body(user)
    }

    @PatchMapping("/{idUser}/detail")
    @Operation(summary = "Actualiza el detalle de un usuario")
    fun updateDetail(
        @PathVariable @UUID idUser: String, @RequestBody user: UserDetailDto
    ): ResponseEntity<UserDetailDto> {
        val originalUser = userService.updateDetail(idUser, user)
        return ResponseEntity.ok().body(originalUser)
    }


}