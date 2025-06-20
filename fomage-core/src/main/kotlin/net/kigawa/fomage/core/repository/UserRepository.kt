package net.kigawa.fomage.core.repository

import net.kigawa.fomage.core.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Repository for accessing User data in MongoDB.
 */
@Repository
interface UserRepository : MongoRepository<User, String> {
    
    /**
     * Finds a user by email.
     */
    fun findByEmail(email: String): User?
    
    /**
     * Finds users by name (case-insensitive, partial match).
     */
    fun findByNameContainingIgnoreCase(name: String): List<User>
}