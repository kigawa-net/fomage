package net.kigawa.fomage.api

/**
 * API interface for schema retrieval and display functionality.
 */
interface SchemaManagerApi {
    /**
     * Gets the schema for a collection.
     */
    fun getSchema(database: String, collection: String): Map<String, String>
}