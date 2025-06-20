package net.kigawa.fomage.core.repository

import net.kigawa.fomage.core.model.GenericDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repository for accessing generic documents in MongoDB.
 * This replaces the previous FonsoleRepository with a more type-safe implementation.
 */
@Repository
interface GenericDocumentRepository : MongoRepository<GenericDocument, String> {
    
    /**
     * Finds documents in a collection.
     */
    fun findByCollection(collection: String): List<GenericDocument>
    
    /**
     * Finds a document by its ID and collection.
     */
    @Query("{ '_id': ?0, 'collection': ?1 }")
    fun findByIdAndCollection(id: String, collection: String): GenericDocument?
}