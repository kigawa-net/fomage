package net.kigawa.fomage.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repository for accessing Fonsole data in MongoDB.
 */
@Repository
interface FonsoleRepository : MongoRepository<Map<String, Any>, String> {

    /**
     * Finds documents in a collection.
     */
    @Query("{ '_collection': ?0 }")
    fun findByCollection(collection: String): List<Map<String, Any>>

    /**
     * Finds a document by its ID.
     */
    @Query("{ '_id': ?0, '_collection': ?1 }")
    fun findByIdAndCollection(id: String, collection: String): Map<String, Any>?
}
