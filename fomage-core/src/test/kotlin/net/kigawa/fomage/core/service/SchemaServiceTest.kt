package net.kigawa.fomage.core.service

import net.kigawa.fomage.core.model.GenericDocument
import net.kigawa.fomage.core.repository.GenericDocumentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.springframework.data.mongodb.core.MongoTemplate
import java.time.Instant

class SchemaServiceTest {

    private lateinit var schemaService: SchemaService
    private lateinit var mongoTemplate: MongoTemplate
    private lateinit var genericDocumentRepository: GenericDocumentRepository

    @BeforeEach
    fun setUp() {
        mongoTemplate = mock(MongoTemplate::class.java)
        genericDocumentRepository = mock(GenericDocumentRepository::class.java)
        schemaService = SchemaService(mongoTemplate, genericDocumentRepository)
    }

    @Test
    fun `getCollectionSchema should return empty map when no documents exist`() {
        // Arrange
        val collectionName = "testCollection"
        `when`(genericDocumentRepository.findByCollection(collectionName)).thenReturn(emptyList())

        // Act
        val result = schemaService.getCollectionSchema(collectionName)

        // Assert
        assertTrue(result.isEmpty())
        verify(genericDocumentRepository).findByCollection(collectionName)
    }

    @Test
    fun `getCollectionSchema should infer schema from documents`() {
        // Arrange
        val collectionName = "testCollection"
        val documents = listOf(
            createGenericDocument(
                collectionName,
                mapOf(
                    "stringField" to "test",
                    "intField" to 123,
                    "boolField" to true,
                    "nullField" to null
                )
            ),
            createGenericDocument(
                collectionName,
                mapOf(
                    "stringField" to "another test",
                    "doubleField" to 123.45,
                    "nullField" to "not null anymore"
                )
            )
        )
        `when`(genericDocumentRepository.findByCollection(collectionName)).thenReturn(documents)

        // Act
        val result = schemaService.getCollectionSchema(collectionName)

        // Assert
        assertEquals(5, result.size)
        assertEquals("String", result["stringField"])
        assertEquals("Integer", result["intField"])
        assertEquals("Boolean", result["boolField"])
        assertEquals("String", result["nullField"]) // Should be updated from null to String
        assertEquals("Double", result["doubleField"])
        verify(genericDocumentRepository).findByCollection(collectionName)
    }

    @Test
    fun `clearSchemaCache should remove specific collection from cache`() {
        // Arrange
        val collectionName = "testCollection"
        val documents = listOf(
            createGenericDocument(
                collectionName,
                mapOf("field" to "value")
            )
        )
        `when`(genericDocumentRepository.findByCollection(collectionName)).thenReturn(documents)

        // First call to populate cache
        schemaService.getCollectionSchema(collectionName)

        // Act
        schemaService.clearSchemaCache(collectionName)

        // Verify repository is called again after cache is cleared
        `when`(genericDocumentRepository.findByCollection(collectionName)).thenReturn(documents)
        schemaService.getCollectionSchema(collectionName)

        // Assert
        verify(genericDocumentRepository).findByCollection(collectionName) // First call
        verify(genericDocumentRepository).findByCollection(collectionName) // Second call after cache clear
    }

    private fun createGenericDocument(collection: String, data: Map<String, Any?>): GenericDocument {
        val document = GenericDocument()
        document.id = "test-id-${System.currentTimeMillis()}"
        document.collection = collection
        document.data = data
        document.createdAt = Instant.now()
        document.updatedAt = Instant.now()
        return document
    }
}