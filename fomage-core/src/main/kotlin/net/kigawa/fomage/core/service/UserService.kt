package net.kigawa.fomage.core.service

import net.kigawa.fomage.core.model.User
import net.kigawa.fomage.core.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Service for user management and authentication.
 */
@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    /**
     * Loads a user by username (email) for authentication.
     */
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found with email: $username")
    }

    /**
     * Creates a new user.
     */
    fun createUser(user: User, rawPassword: String): User {
        user.setPassword(passwordEncoder.encode(rawPassword))
        return userRepository.save(user)
    }

    /**
     * Updates an existing user.
     */
    fun updateUser(user: User): User {
        return userRepository.save(user)
    }

    /**
     * Changes a user's password.
     */
    fun changePassword(userId: String, newPassword: String): User {
        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("User not found with ID: $userId") }
        user.setPassword(passwordEncoder.encode(newPassword))
        return userRepository.save(user)
    }

    /**
     * Finds a user by ID.
     */
    fun findById(id: String): User? {
        return userRepository.findById(id).orElse(null)
    }

    /**
     * Finds a user by email.
     */
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    /**
     * Finds all users.
     */
    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}