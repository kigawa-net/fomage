package net.kigawa.fomage.core.repository

import net.kigawa.fomage.core.model.Data
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Repository for accessing Data documents in MongoDB.
 */
@Repository
interface DataRepository : MongoRepository<Data, String> {
    
    /**
     * Finds data by type.
     */
    fun findByType(type: String): List<Data>
    
    /**
     * Finds data by user ID.
     */
    fun findByUserId(userId: String): List<Data>
    
    /**
     * Finds data by type and user ID.
     */
    fun findByTypeAndUserId(type: String, userId: String): List<Data>
}