package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<User, String> {

//    var principals: MutableMap<String, Principal>
//    fun create(password: String, user: User) {
//        super.create(user)
//        principals[user.id] = Principal().apply {
//            this.username = user.email
//            this.password = password
//            this.user = user
//
//            this.initProperties()
//        }
//    }

    fun findByUsername(email: String): Optional<User>
}