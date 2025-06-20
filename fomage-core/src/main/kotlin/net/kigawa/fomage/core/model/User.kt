package net.kigawa.fomage.core.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * User information model.
 * 
 * Fields:
 * - id: Unique user ID
 * - name: User name
 * - email: Email address
 * - password: Hashed password
 * - role: User role (ADMIN, USER)
 * - createdAt: Creation date and time
 * - updatedAt: Last update date and time
 */
@Document(collection = "users")
class User : BaseModel(), UserDetails {
    var name: String? = null
    var email: String? = null
    private var password: String? = null
    var role: String = "USER"

    // UserDetails implementation
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_$role"))
    }

    override fun getPassword(): String? = password

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername(): String = email ?: ""

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
