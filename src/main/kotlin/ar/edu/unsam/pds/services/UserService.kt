package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService {
    private var userRepository = UserRepository

    fun getAssignmentAll(): List<User> {
        return userRepository.getAll().toList()
    }

}