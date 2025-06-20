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
     * Gets a document by ID.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param id The document ID
     * @return The document if found, null otherwise
     */
    fun getDocumentById(database: String, collection: String, id: String): GenericDocument? {
        return dataService.findDocumentById(database, collection, id)
    }

    /**
     * Creates a new document.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param document The document to create
     * @return The created document
     */
    fun createDocument(database: String, collection: String, document: GenericDocument): GenericDocument {
        return dataService.createDocument(database, collection, document)
    }

    /**
     * Updates an existing document.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param id The document ID
     * @param document The updated document
     * @return The updated document
     */
    fun updateDocument(database: String, collection: String, id: String, document: GenericDocument): GenericDocument {
        return dataService.updateDocument(database, collection, id, document)
    }

    /**
     * Deletes a document.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param id The document ID
     */
    fun deleteDocument(database: String, collection: String, id: String) {
        dataService.deleteDocument(database, collection, id)
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
     * Gets a user by ID.
     * 
     * @param id The user ID
     * @return The user if found, null otherwise
     */
    fun getUserById(id: String): User? {
        return dataService.findUserById(id)
    }

    /**
     * Creates a new user.
     * 
     * @param user The user to create
     * @return The created user
     */
    fun createUser(user: User): User {
        return dataService.createUser(user)
    }

    /**
     * Updates an existing user.
     * 
     * @param id The user ID
     * @param user The updated user
     * @return The updated user
     */
    fun updateUser(id: String, user: User): User {
        return dataService.updateUser(id, user)
    }

    /**
     * Deletes a user.
     * 
     * @param id The user ID
     */
    fun deleteUser(id: String) {
        dataService.deleteUser(id)
    }

    /**
     * Gets a list of all data documents.
     * 
     * @return A list of all data documents
     */
    fun getData(): List<Data> {
        return dataService.findAllData()
    }

    /**
     * Gets a data document by ID.
     * 
     * @param id The data ID
     * @return The data document if found, null otherwise
     */
    fun getDataById(id: String): Data? {
        return dataService.findDataById(id)
    }

    /**
     * Creates a new data document.
     * 
     * @param data The data document to create
     * @return The created data document
     */
    fun createData(data: Data): Data {
        return dataService.createData(data)
    }

    /**
     * Updates an existing data document.
     * 
     * @param id The data ID
     * @param data The updated data document
     * @return The updated data document
     */
    fun updateData(id: String, data: Data): Data {
        return dataService.updateData(id, data)
    }

    /**
     * Deletes a data document.
     * 
     * @param id The data ID
     */
    fun deleteData(id: String) {
        dataService.deleteData(id)
    }
}
