package net.kigawa.fomage.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repository for accessing Fonsole data in MongoDB.
 * 
 * @deprecated This repository uses non-type-safe Map<String, Any> as the entity type.
 * Use the following type-safe repositories instead:
 * - [UserRepository] for user data
 * - [DataRepository] for generic data
 * - [GenericDocumentRepository] for other collections
 */
@Deprecated("Use type-safe repositories instead: UserRepository, DataRepository, GenericDocumentRepository")
@Repository
interface FonsoleRepository : MongoRepository<Map<String, Any>, String> {

    /**
     * Finds documents in a collection.
     * 
     * @deprecated Use [GenericDocumentRepository.findByCollection] instead
     */
    @Deprecated("Use GenericDocumentRepository.findByCollection instead")
    @Query("{ '_collection': ?0 }")
    fun findByCollection(collection: String): List<Map<String, Any>>

    /**
     * Finds a document by its ID.
     * 
     * @deprecated Use [GenericDocumentRepository.findByIdAndCollection] instead
     */
    @Deprecated("Use GenericDocumentRepository.findByIdAndCollection instead")
    @Query("{ '_id': ?0, '_collection': ?1 }")
    fun findByIdAndCollection(id: String, collection: String): Map<String, Any>?
}
