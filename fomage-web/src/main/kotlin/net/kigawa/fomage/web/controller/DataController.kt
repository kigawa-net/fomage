package net.kigawa.fomage.web.controller

import net.kigawa.fomage.web.service.ApiClientService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Controller for data viewing pages.
 */
@Controller
class DataController(private val apiClientService: ApiClientService) {

    /**
     * Shows the database list page.
     */
    @GetMapping("/")
    fun showDatabaseList(model: Model): String {
        val databases = apiClientService.getDatabases()
        model.addAttribute("databases", databases)
        return "data/list"
    }

    /**
     * Shows the collection list page.
     */
    @GetMapping("/collections")
    fun showCollectionList(@RequestParam database: String, model: Model): String {
        val collections = apiClientService.getCollections(database)
        model.addAttribute("database", database)
        model.addAttribute("collections", collections)
        return "data/collections"
    }

    /**
     * Shows the document list page.
     */
    @GetMapping("/documents")
    fun showDocumentList(
        @RequestParam database: String,
        @RequestParam collection: String,
        model: Model
    ): String {
        // Handle special collections with specific models
        if (collection == "users") {
            return showUserList(model)
        } else if (collection == "data") {
            return showDataList(model)
        }

        // For other collections, use generic documents
        val documents = apiClientService.getDocuments(database, collection)
        model.addAttribute("database", database)
        model.addAttribute("collection", collection)
        model.addAttribute("documents", documents)
        return "data/documents"
    }

    /**
     * Shows the user list page.
     */
    @GetMapping("/users")
    fun showUserList(model: Model): String {
        val users = apiClientService.getUsers()
        model.addAttribute("users", users)
        return "data/users"
    }

    /**
     * Shows the data list page.
     */
    @GetMapping("/data")
    fun showDataList(model: Model): String {
        val dataList = apiClientService.getData()
        model.addAttribute("dataList", dataList)
        return "data/data"
    }
}
