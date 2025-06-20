package net.kigawa.fomage.core.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * Base class for all MongoDB document models.
 */
abstract class BaseModel {
    @Id
    var id: String? = null
    
    var createdAt: Instant? = null
    var updatedAt: Instant? = null
}