package net.kigawa.fomage.core.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * Generic document model for collections that don't have a specific model class.
 * This provides better type safety than using raw Map<String, Any>.
 */
@Document
class GenericDocument {
    @Id
    var id: String? = null
    
    var collection: String? = null
    var data: Map<String, Any> = mutableMapOf()
    var createdAt: Instant? = null
    var updatedAt: Instant? = null
}