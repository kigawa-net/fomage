package net.kigawa.fomage.api

import net.kigawa.fomage.core.model.Data
import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.model.User

/**
 * API interface for data viewing and editing functionality.
 */
interface DataManagerApi {
    /**
     * Lists all databases in the MongoDB instance.
     */
    fun listDatabases(): List<String>

    /**
     * Lists all collections in a database.
     */
    fun listCollections(database: String): List<String>

    /**
     * Finds documents in a collection.
     * 
     * @param database The database name
     * @param collection The collection name
     * @return A list of generic documents if the collection doesn't have a specific model,
     *         otherwise returns an empty list (use the specific methods instead)
     */
    fun findDocuments(database: String, collection: String): List<GenericDocument>

    /**
     * Finds all users.
     * 
     * @return A list of all users
     */
    fun findAllUsers(): List<User>

    /**
     * Finds all data documents.
     * 
     * @return A list of all data documents
     */
    fun findAllData(): List<Data>
}
