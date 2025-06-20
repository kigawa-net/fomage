package net.kigawa.fomage.core.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Generic data model.
 * 
 * Fields:
 * - id: Unique data ID
 * - type: Data type (e.g., "document", "image")
 * - content: Data content
 * - userId: Owner user ID
 * - createdAt: Creation date and time
 * - updatedAt: Last update date and time
 */
@Document(collection = "data")
class Data : BaseModel() {
    var type: String? = null
    var content: Any? = null
    var userId: String? = null
}