package net.kigawa.fomage.api.service

import net.kigawa.fomage.api.DataManagerApi
import net.kigawa.fomage.core.model.Data
import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.model.User
import net.kigawa.fomage.core.repository.DataRepository
import net.kigawa.fomage.core.repository.GenericDocumentRepository
import net.kigawa.fomage.core.repository.UserRepository
import org.springframework.stereotype.Service

/**
 * Service for data management operations.
 */
@Service
class DataService(
    private val genericDocumentRepository: GenericDocumentRepository,
    private val userRepository: UserRepository,
    private val dataRepository: DataRepository
) : DataManagerApi {

    /**
     * Lists all databases in the MongoDB instance.
     */
    override fun listDatabases(): List<String> {
        // In a real implementation, this would query MongoDB for all databases
        return listOf("fonsole", "admin", "local")
    }

    /**
     * Lists all collections in a database.
     */
    override fun listCollections(database: String): List<String> {
        // In a real implementation, this would query MongoDB for all collections in the database
        return listOf("backups", "projects", "users", "data", "fs.files")
    }

    /**
     * Finds documents in a collection.
     */
    override fun findDocuments(database: String, collection: String): List<GenericDocument> {
        // For collections that have specific models, return empty list
        if (collection == "users" || collection == "data") {
            return emptyList()
        }

        // For other collections, use the generic document repository
        return genericDocumentRepository.findByCollection(collection)
    }

    /**
     * Finds all users.
     */
    override fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }

    /**
     * Finds all data documents.
     */
    override fun findAllData(): List<Data> {
        return dataRepository.findAll()
    }
}
