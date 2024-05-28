package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID>