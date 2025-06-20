package net.kigawa.fomage.web.controller

import net.kigawa.fomage.core.service.SchemaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * Controller for schema management pages.
 */
@Controller
@RequestMapping("/schema")
class SchemaController(private val schemaService: SchemaService) {

    /**
     * Shows the schema for a collection.
     */
    @GetMapping
    fun showSchema(@RequestParam collection: String, model: Model): String {
        val schema = schemaService.getCollectionSchema(collection)
        
        model.addAttribute("collection", collection)
        model.addAttribute("schema", schema)
        
        return "schema/view"
    }
    
    /**
     * Refreshes the schema for a collection.
     */
    @PostMapping("/refresh")
    fun refreshSchema(
        @RequestParam collection: String,
        redirectAttributes: RedirectAttributes
    ): String {
        schemaService.clearSchemaCache(collection)
        redirectAttributes.addFlashAttribute("message", "Schema cache cleared for collection: $collection")
        
        return "redirect:/schema?collection=$collection"
    }
    
    /**
     * Refreshes all schema caches.
     */
    @PostMapping("/refresh-all")
    fun refreshAllSchemas(redirectAttributes: RedirectAttributes): String {
        schemaService.clearAllSchemaCaches()
        redirectAttributes.addFlashAttribute("message", "All schema caches cleared")
        
        return "redirect:/dashboard"
    }
}