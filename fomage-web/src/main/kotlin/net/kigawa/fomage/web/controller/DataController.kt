package net.kigawa.fomage.web.controller

import net.kigawa.fomage.core.model.Data
import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.model.User
import net.kigawa.fomage.web.service.ApiClientService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.security.Principal

/**
 * Controller for data viewing and management pages.
 */
@Controller
class DataController(private val apiClientService: ApiClientService) {

    /**
     * Shows the database list page.
     */
    @GetMapping("/")
    fun showDatabaseList(model: Model, principal: Principal): String {
        val databases = apiClientService.getDatabases()
        model.addAttribute("databases", databases)
        model.addAttribute("username", principal.name)
        return "data/list"
    }

    /**
     * Shows the collection list page.
     */
    @GetMapping("/collections")
    fun showCollectionList(@RequestParam database: String, model: Model, principal: Principal): String {
        val collections = apiClientService.getCollections(database)
        model.addAttribute("database", database)
        model.addAttribute("collections", collections)
        model.addAttribute("username", principal.name)
        return "data/collections"
    }

    /**
     * Shows the document list page.
     */
    @GetMapping("/documents")
    fun showDocumentList(
        @RequestParam database: String,
        @RequestParam collection: String,
        model: Model,
        principal: Principal
    ): String {
        // Handle special collections with specific models
        if (collection == "users") {
            return showUserList(model, principal)
        } else if (collection == "data") {
            return showDataList(model, principal)
        }

        // For other collections, use generic documents
        val documents = apiClientService.getDocuments(database, collection)
        model.addAttribute("database", database)
        model.addAttribute("collection", collection)
        model.addAttribute("documents", documents)
        model.addAttribute("username", principal.name)
        return "data/documents"
    }

    /**
     * Shows the document detail page.
     */
    @GetMapping("/documents/{id}")
    fun showDocumentDetail(
        @RequestParam database: String,
        @RequestParam collection: String,
        @PathVariable id: String,
        model: Model,
        principal: Principal
    ): String {
        val document = apiClientService.getDocumentById(database, collection, id)
            ?: throw IllegalArgumentException("Document not found with ID: $id")

        model.addAttribute("database", database)
        model.addAttribute("collection", collection)
        model.addAttribute("document", document)
        model.addAttribute("username", principal.name)
        return "data/document-detail"
    }

    /**
     * Shows the document creation page.
     */
    @GetMapping("/documents/new")
    fun showDocumentCreationForm(
        @RequestParam database: String,
        @RequestParam collection: String,
        model: Model,
        principal: Principal
    ): String {
        model.addAttribute("database", database)
        model.addAttribute("collection", collection)
        model.addAttribute("document", GenericDocument())
        model.addAttribute("username", principal.name)
        return "data/document-form"
    }

    /**
     * Creates a new document.
     */
    @PostMapping("/documents")
    fun createDocument(
        @RequestParam database: String,
        @RequestParam collection: String,
        @ModelAttribute document: GenericDocument,
        redirectAttributes: RedirectAttributes
    ): String {
        val createdDocument = apiClientService.createDocument(database, collection, document)
        redirectAttributes.addFlashAttribute("message", "Document created successfully")
        return "redirect:/documents?database=$database&collection=$collection"
    }

    /**
     * Shows the document edit page.
     */
    @GetMapping("/documents/{id}/edit")
    fun showDocumentEditForm(
        @RequestParam database: String,
        @RequestParam collection: String,
        @PathVariable id: String,
        model: Model,
        principal: Principal
    ): String {
        val document = apiClientService.getDocumentById(database, collection, id)
            ?: throw IllegalArgumentException("Document not found with ID: $id")

        model.addAttribute("database", database)
        model.addAttribute("collection", collection)
        model.addAttribute("document", document)
        model.addAttribute("username", principal.name)
        return "data/document-form"
    }

    /**
     * Updates an existing document.
     */
    @PostMapping("/documents/{id}")
    fun updateDocument(
        @RequestParam database: String,
        @RequestParam collection: String,
        @PathVariable id: String,
        @ModelAttribute document: GenericDocument,
        redirectAttributes: RedirectAttributes
    ): String {
        apiClientService.updateDocument(database, collection, id, document)
        redirectAttributes.addFlashAttribute("message", "Document updated successfully")
        return "redirect:/documents?database=$database&collection=$collection"
    }

    /**
     * Deletes a document.
     */
    @PostMapping("/documents/{id}/delete")
    fun deleteDocument(
        @RequestParam database: String,
        @RequestParam collection: String,
        @PathVariable id: String,
        redirectAttributes: RedirectAttributes
    ): String {
        apiClientService.deleteDocument(database, collection, id)
        redirectAttributes.addFlashAttribute("message", "Document deleted successfully")
        return "redirect:/documents?database=$database&collection=$collection"
    }

    /**
     * Shows the user list page.
     */
    @GetMapping("/users")
    fun showUserList(model: Model, principal: Principal): String {
        val users = apiClientService.getUsers()
        model.addAttribute("users", users)
        model.addAttribute("username", principal.name)
        return "data/users"
    }

    /**
     * Shows the user detail page.
     */
    @GetMapping("/users/{id}")
    fun showUserDetail(@PathVariable id: String, model: Model, principal: Principal): String {
        val user = apiClientService.getUserById(id)
            ?: throw IllegalArgumentException("User not found with ID: $id")

        model.addAttribute("user", user)
        model.addAttribute("username", principal.name)
        return "data/user-detail"
    }

    /**
     * Shows the user creation page.
     */
    @GetMapping("/users/new")
    fun showUserCreationForm(model: Model, principal: Principal): String {
        model.addAttribute("user", User())
        model.addAttribute("username", principal.name)
        return "data/user-form"
    }

    /**
     * Creates a new user.
     */
    @PostMapping("/users")
    fun createUser(@ModelAttribute user: User, redirectAttributes: RedirectAttributes): String {
        val createdUser = apiClientService.createUser(user)
        redirectAttributes.addFlashAttribute("message", "User created successfully")
        return "redirect:/users"
    }

    /**
     * Shows the user edit page.
     */
    @GetMapping("/users/{id}/edit")
    fun showUserEditForm(@PathVariable id: String, model: Model, principal: Principal): String {
        val user = apiClientService.getUserById(id)
            ?: throw IllegalArgumentException("User not found with ID: $id")

        model.addAttribute("user", user)
        model.addAttribute("username", principal.name)
        return "data/user-form"
    }

    /**
     * Updates an existing user.
     */
    @PostMapping("/users/{id}")
    fun updateUser(
        @PathVariable id: String,
        @ModelAttribute user: User,
        redirectAttributes: RedirectAttributes
    ): String {
        apiClientService.updateUser(id, user)
        redirectAttributes.addFlashAttribute("message", "User updated successfully")
        return "redirect:/users"
    }

    /**
     * Deletes a user.
     */
    @PostMapping("/users/{id}/delete")
    fun deleteUser(@PathVariable id: String, redirectAttributes: RedirectAttributes): String {
        apiClientService.deleteUser(id)
        redirectAttributes.addFlashAttribute("message", "User deleted successfully")
        return "redirect:/users"
    }

    /**
     * Shows the data list page.
     */
    @GetMapping("/data")
    fun showDataList(model: Model, principal: Principal): String {
        val dataList = apiClientService.getData()
        model.addAttribute("dataList", dataList)
        model.addAttribute("username", principal.name)
        return "data/data"
    }

    /**
     * Shows the data detail page.
     */
    @GetMapping("/data/{id}")
    fun showDataDetail(@PathVariable id: String, model: Model, principal: Principal): String {
        val data = apiClientService.getDataById(id)
            ?: throw IllegalArgumentException("Data not found with ID: $id")

        model.addAttribute("data", data)
        model.addAttribute("username", principal.name)
        return "data/data-detail"
    }

    /**
     * Shows the data creation page.
     */
    @GetMapping("/data/new")
    fun showDataCreationForm(model: Model, principal: Principal): String {
        model.addAttribute("data", Data())
        model.addAttribute("username", principal.name)
        return "data/data-form"
    }

    /**
     * Creates a new data document.
     */
    @PostMapping("/data")
    fun createData(@ModelAttribute data: Data, redirectAttributes: RedirectAttributes): String {
        val createdData = apiClientService.createData(data)
        redirectAttributes.addFlashAttribute("message", "Data created successfully")
        return "redirect:/data"
    }

    /**
     * Shows the data edit page.
     */
    @GetMapping("/data/{id}/edit")
    fun showDataEditForm(@PathVariable id: String, model: Model, principal: Principal): String {
        val data = apiClientService.getDataById(id)
            ?: throw IllegalArgumentException("Data not found with ID: $id")

        model.addAttribute("data", data)
        model.addAttribute("username", principal.name)
        return "data/data-form"
    }

    /**
     * Updates an existing data document.
     */
    @PostMapping("/data/{id}")
    fun updateData(
        @PathVariable id: String,
        @ModelAttribute data: Data,
        redirectAttributes: RedirectAttributes
    ): String {
        apiClientService.updateData(id, data)
        redirectAttributes.addFlashAttribute("message", "Data updated successfully")
        return "redirect:/data"
    }

    /**
     * Deletes a data document.
     */
    @PostMapping("/data/{id}/delete")
    fun deleteData(@PathVariable id: String, redirectAttributes: RedirectAttributes): String {
        apiClientService.deleteData(id)
        redirectAttributes.addFlashAttribute("message", "Data deleted successfully")
        return "redirect:/data"
    }
}
