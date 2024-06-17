package ar.edu.unsam.pds.security.models

import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.models.User
import jakarta.persistence.*
import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.FetchType.EAGER
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity @Table(name = "SPRING_PRINCIPAL")
class Principal : UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    private var username: String? = null
    private var password: String? = null
    private var accountNonExpired: Boolean? = null
    private var accountNonLocked: Boolean? = null          // This is done by the system, for example, invalid logins.
    private var credentialsNonExpired: Boolean? = null
    private var enabled: Boolean? = null                   // This is done by the system administrator
    @OneToOne(fetch = EAGER, cascade = [PERSIST])
    var user: User? = null

    // region UserDetails @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    fun setUsername(username: String) { this.username = username }
    fun setPassword(password: String) { this.password = password }

    override fun getUsername(): String = username!!
    override fun getPassword(): String = password!!
    override fun isAccountNonExpired(): Boolean = accountNonExpired!!
    override fun isAccountNonLocked(): Boolean = accountNonLocked!!
    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired!!
    override fun isEnabled(): Boolean = enabled!!

    override fun getAuthorities(): Collection<GrantedAuthority> {
        if (user == null ) throw InternalServerError("Usuario no encontrado")
        val role = if (user!!.isAdmin) "ROLE_ADMIN" else "ROLE_USER"
        return mutableListOf(SimpleGrantedAuthority(role))
    }
    // endregion @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    fun initProperties() {
        this.accountNonExpired = true
        this.accountNonLocked = true
        this.credentialsNonExpired = true
        this.enabled = true
    }
}