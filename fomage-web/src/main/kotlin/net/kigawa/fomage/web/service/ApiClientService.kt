package net.kigawa.fomage.web.service

import net.kigawa.fomage.api.service.DataService
import net.kigawa.fomage.core.model.Data
import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.model.User
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
     * 
     * @param database The database name
     * @param collection The collection name
     * @return A list of generic documents if the collection doesn't have a specific model,
     *         otherwise returns an empty list (use the specific methods instead)
     */
    fun getDocuments(database: String, collection: String): List<GenericDocument> {
        return dataService.findDocuments(database, collection)
    }

    /**
     * Gets a list of all users.
     * 
     * @return A list of all users
     */
    fun getUsers(): List<User> {
        return dataService.findAllUsers()
    }

    /**
     * Gets a list of all data documents.
     * 
     * @return A list of all data documents
     */
    fun getData(): List<Data> {
        return dataService.findAllData()
    }
}
