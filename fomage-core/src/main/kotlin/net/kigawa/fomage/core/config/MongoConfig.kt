package net.kigawa.fomage.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.beans.factory.annotation.Value
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients

/**
 * MongoDB configuration for the Fomage application.
 */
@Configuration
@EnableMongoRepositories(basePackages = ["net.kigawa.fomage.core.repository"])
open class MongoConfig : AbstractMongoClientConfiguration() {

    @Value("\${spring.data.mongodb.uri:mongodb://localhost:27017/fonsole}")
    private lateinit var mongoUri: String

    @Value("\${spring.data.mongodb.database:fonsole}")
    private lateinit var mongoDatabaseName: String

    /**
     * Creates a MongoClient using the configured URI.
     */
    override fun mongoClient(): MongoClient {
        return MongoClients.create(mongoUri)
    }

    /**
     * Returns the name of the database to use.
     */
    override fun getDatabaseName(): String {
        return mongoDatabaseName
    }
}
