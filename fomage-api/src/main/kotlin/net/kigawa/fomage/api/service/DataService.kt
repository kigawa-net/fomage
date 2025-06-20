package net.kigawa.fomage.api.service

import net.kigawa.fomage.api.DataManagerApi
import net.kigawa.fomage.core.model.Data
import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.model.User
import net.kigawa.fomage.core.repository.DataRepository
import net.kigawa.fomage.core.repository.GenericDocumentRepository
import net.kigawa.fomage.core.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Optional

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
     * Finds a document by ID.
     */
    override fun findDocumentById(database: String, collection: String, id: String): GenericDocument? {
        // For collections that have specific models, return null
        if (collection == "users" || collection == "data") {
            return null
        }

        return genericDocumentRepository.findById(id).orElse(null)
    }

    /**
     * Creates a new document.
     */
    override fun createDocument(database: String, collection: String, document: GenericDocument): GenericDocument {
        // Set collection name and timestamps
        document.collection = collection
        document.createdAt = Instant.now()
        document.updatedAt = document.createdAt

        return genericDocumentRepository.save(document)
    }

    /**
     * Updates an existing document.
     */
    override fun updateDocument(database: String, collection: String, id: String, document: GenericDocument): GenericDocument {
        val existingDocument = genericDocumentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Document not found with ID: $id") }

        // Update fields
        document.id = id
        document.collection = collection
        document.createdAt = existingDocument.createdAt
        document.updatedAt = Instant.now()

        return genericDocumentRepository.save(document)
    }

    /**
     * Deletes a document.
     */
    override fun deleteDocument(database: String, collection: String, id: String) {
        genericDocumentRepository.deleteById(id)
    }

    /**
     * Finds all users.
     */
    override fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }

    /**
     * Finds a user by ID.
     */
    override fun findUserById(id: String): User? {
        return userRepository.findById(id).orElse(null)
    }

    /**
     * Creates a new user.
     */
    override fun createUser(user: User): User {
        // Set timestamps
        user.createdAt = Instant.now()
        user.updatedAt = user.createdAt

        return userRepository.save(user)
    }

    /**
     * Updates an existing user.
     */
    override fun updateUser(id: String, user: User): User {
        val existingUser = userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("User not found with ID: $id") }

        // Update fields
        user.id = id
        user.createdAt = existingUser.createdAt
        user.updatedAt = Instant.now()

        return userRepository.save(user)
    }

    /**
     * Deletes a user.
     */
    override fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }

    /**
     * Finds all data documents.
     */
    override fun findAllData(): List<Data> {
        return dataRepository.findAll()
    }

    /**
     * Finds a data document by ID.
     */
    override fun findDataById(id: String): Data? {
        return dataRepository.findById(id).orElse(null)
    }

    /**
     * Creates a new data document.
     */
    override fun createData(data: Data): Data {
        // Set timestamps
        data.createdAt = Instant.now()
        data.updatedAt = data.createdAt

        return dataRepository.save(data)
    }

    /**
     * Updates an existing data document.
     */
    override fun updateData(id: String, data: Data): Data {
        val existingData = dataRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Data not found with ID: $id") }

        // Update fields
        data.id = id
        data.createdAt = existingData.createdAt
        data.updatedAt = Instant.now()

        return dataRepository.save(data)
    }

    /**
     * Deletes a data document.
     */
    override fun deleteData(id: String) {
        dataRepository.deleteById(id)
    }
}
