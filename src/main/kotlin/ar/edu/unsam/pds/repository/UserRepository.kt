package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import java.util.Optional

object UserRepository : Repository<User>() {
    private var principals: MutableMap<String, Principal> = hashMapOf()

    override fun create(element: User) {
        error("""
            Principal se encuentra vinculado a User
            por favor usar UserRepository.create(password: String, user: User)
        """)
    }

    fun create(password: String, user: User) {
        super.create(user)
        principals[user.id] = Principal().apply {
            this.username = user.email
            this.password = password
            this.user = user

            this.initProperties()
        }
    }

    fun findByUsername(email: String): Optional<Principal> {
        val principal = principals.values.firstOrNull { it.username == email }
        return Optional.ofNullable(principal)
    }
}