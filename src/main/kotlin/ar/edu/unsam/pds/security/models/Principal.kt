package ar.edu.unsam.pds.security.models

import ar.edu.unsam.pds.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class Principal : UserDetails {
    private var username: String? = null
    private var password: String? = null
    private var accountNonExpired: Boolean? = null
    private var accountNonLocked: Boolean? = null          // This is done by the system, for example, invalid logins.
    private var credentialsNonExpired: Boolean? = null
    private var enabled: Boolean? = null                   // This is done by the system administrator

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
        return mutableListOf(SimpleGrantedAuthority("ROLE_UNKNOWN"))
    }
    // endregion @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    fun initProperties() {
        this.accountNonExpired = true
        this.accountNonLocked = true
        this.credentialsNonExpired = true
        this.enabled = true
    }
}