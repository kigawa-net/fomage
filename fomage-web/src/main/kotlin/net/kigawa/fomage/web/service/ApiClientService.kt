package net.kigawa.fomage.web.service

import net.kigawa.fomage.api.service.DataService
import org.springframework.stereotype.Service

/**
 * Service for communicating with the API module.
 */
@Service
class ApiClientService(
    private val dataService: DataService
) {
    /**
     * Gets a list of all databases.
     */
    fun getDatabases(): List<String> {
        return dataService.listDatabases()
    }

    /**
     * Gets a list of all collections in a database.
     */
    fun getCollections(database: String): List<String> {
        return dataService.listCollections(database)
    }

    /**
     * Gets a list of documents in a collection.
     */
    fun getDocuments(database: String, collection: String): List<Map<String, Any>> {
        return dataService.findDocuments(database, collection)
    }
}