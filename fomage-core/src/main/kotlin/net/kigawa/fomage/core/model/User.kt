package net.kigawa.fomage.core.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * User information model.
 * 
 * Fields:
 * - id: Unique user ID
 * - name: User name
 * - email: Email address
 * - createdAt: Creation date and time
 * - updatedAt: Last update date and time
 */
@Document(collection = "users")
class User : BaseModel() {
    var name: String? = null
    var email: String? = null
}