package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.services.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/")
@Validated
@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET])
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping(value = ["users"])
    @Operation(summary = "Get all users")
    fun userAll(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userService.getUserAll())
    }

}