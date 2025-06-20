package net.kigawa.fomage.core.service

import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.repository.GenericDocumentRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

/**
 * Service for schema management operations.
 */
@Service
class SchemaService(
    private val mongoTemplate: MongoTemplate,
    private val genericDocumentRepository: GenericDocumentRepository
) {
    // Cache for collection schemas
    private val schemaCache = ConcurrentHashMap<String, Map<String, String>>()
    
    /**
     * Gets the schema for a collection.
     * 
     * @param collection The collection name
     * @return A map of field names to field types
     */
    fun getCollectionSchema(collection: String): Map<String, String> {
        // Check cache first
        if (schemaCache.containsKey(collection)) {
            return schemaCache[collection]!!
        }
        
        // Get a sample of documents from the collection
        val documents = genericDocumentRepository.findByCollection(collection).take(10)
        
        // If no documents, return empty schema
        if (documents.isEmpty()) {
            val emptySchema = emptyMap<String, String>()
            schemaCache[collection] = emptySchema
            return emptySchema
        }
        
        // Analyze documents to infer schema
        val schema = inferSchema(documents)
        
        // Cache the schema
        schemaCache[collection] = schema
        
        return schema
    }
    
    /**
     * Clears the schema cache for a collection.
     * 
     * @param collection The collection name
     */
    fun clearSchemaCache(collection: String) {
        schemaCache.remove(collection)
    }
    
    /**
     * Clears the entire schema cache.
     */
    fun clearAllSchemaCaches() {
        schemaCache.clear()
    }
    
    /**
     * Infers a schema from a list of documents.
     * 
     * @param documents The documents to analyze
     * @return A map of field names to field types
     */
    private fun inferSchema(documents: List<GenericDocument>): Map<String, String> {
        val schema = mutableMapOf<String, String>()
        
        // Analyze each document
        for (document in documents) {
            for ((key, value) in document.data) {
                val type = when (value) {
                    is String -> "String"
                    is Int -> "Integer"
                    is Long -> "Long"
                    is Double -> "Double"
                    is Boolean -> "Boolean"
                    is Map<*, *> -> "Object"
                    is List<*> -> "Array"
                    null -> "Null"
                    else -> "Unknown"
                }
                
                // Add to schema if not already present or if current type is null
                if (!schema.containsKey(key) || schema[key] == "Null") {
                    schema[key] = type
                }
            }
        }
        
        return schema
    }
}