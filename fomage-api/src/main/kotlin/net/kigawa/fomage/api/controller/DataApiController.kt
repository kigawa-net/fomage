package net.kigawa.fomage.api.controller

import net.kigawa.fomage.api.service.DataService
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
    ): List<Map<String, Any>> {
        return dataService.findDocuments(database, collection)
    }
}