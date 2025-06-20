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
     * Finds a document by ID.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param id The document ID
     * @return The document if found, null otherwise
     */
    fun findDocumentById(database: String, collection: String, id: String): GenericDocument?

    /**
     * Creates a new document.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param document The document to create
     * @return The created document
     */
    fun createDocument(database: String, collection: String, document: GenericDocument): GenericDocument

    /**
     * Updates an existing document.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param id The document ID
     * @param document The updated document
     * @return The updated document
     */
    fun updateDocument(database: String, collection: String, id: String, document: GenericDocument): GenericDocument

    /**
     * Deletes a document.
     * 
     * @param database The database name
     * @param collection The collection name
     * @param id The document ID
     */
    fun deleteDocument(database: String, collection: String, id: String)

    /**
     * Finds all users.
     * 
     * @return A list of all users
     */
    fun findAllUsers(): List<User>

    /**
     * Finds a user by ID.
     * 
     * @param id The user ID
     * @return The user if found, null otherwise
     */
    fun findUserById(id: String): User?

    /**
     * Creates a new user.
     * 
     * @param user The user to create
     * @return The created user
     */
    fun createUser(user: User): User

    /**
     * Updates an existing user.
     * 
     * @param id The user ID
     * @param user The updated user
     * @return The updated user
     */
    fun updateUser(id: String, user: User): User

    /**
     * Deletes a user.
     * 
     * @param id The user ID
     */
    fun deleteUser(id: String)

    /**
     * Finds all data documents.
     * 
     * @return A list of all data documents
     */
    fun findAllData(): List<Data>

    /**
     * Finds a data document by ID.
     * 
     * @param id The data ID
     * @return The data document if found, null otherwise
     */
    fun findDataById(id: String): Data?

    /**
     * Creates a new data document.
     * 
     * @param data The data document to create
     * @return The created data document
     */
    fun createData(data: Data): Data

    /**
     * Updates an existing data document.
     * 
     * @param id The data ID
     * @param data The updated data document
     * @return The updated data document
     */
    fun updateData(id: String, data: Data): Data

    /**
     * Deletes a data document.
     * 
     * @param id The data ID
     */
    fun deleteData(id: String)
}
