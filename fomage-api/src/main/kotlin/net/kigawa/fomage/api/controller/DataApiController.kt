package net.kigawa.fomage.api.controller

import net.kigawa.fomage.api.service.DataService
import net.kigawa.fomage.core.model.Data
import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller for data management API.
 */
@RestController
@RequestMapping("/api/data")
class DataApiController(private val dataService: DataService) {

    /**
     * Lists all databases in the MongoDB instance.
     */
    @GetMapping("/databases")
    fun listDatabases(): List<String> {
        return dataService.listDatabases()
    }

    /**
     * Lists all collections in a database.
     */
    @GetMapping("/collections")
    fun listCollections(@RequestParam database: String): List<String> {
        return dataService.listCollections(database)
    }

    /**
     * Finds documents in a collection.
     */
    @GetMapping("/documents")
    fun findDocuments(
        @RequestParam database: String,
        @RequestParam collection: String
    ): List<GenericDocument> {
        return dataService.findDocuments(database, collection)
    }

    /**
     * Finds all users.
     */
    @GetMapping("/users")
    fun findAllUsers(): List<User> {
        return dataService.findAllUsers()
    }

    /**
     * Finds all data documents.
     */
    @GetMapping("/data")
    fun findAllData(): List<Data> {
        return dataService.findAllData()
    }
}
