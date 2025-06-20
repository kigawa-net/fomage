package net.kigawa.fomage.api

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
     */
    fun findDocuments(database: String, collection: String): List<Map<String, Any>>
}