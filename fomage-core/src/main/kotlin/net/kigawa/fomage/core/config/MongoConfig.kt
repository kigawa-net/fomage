package net.kigawa.fomage.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.beans.factory.annotation.Value
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.core.env.Environment

/**
 * MongoDB configuration for the Fomage application.
 */
@Configuration
@EnableMongoRepositories(basePackages = ["net.kigawa.fomage.core.repository"])
open class MongoConfig(private val env: Environment) : AbstractMongoClientConfiguration() {

    /**
     * Creates a MongoClient using the configured URI.
     */
    override fun mongoClient(): MongoClient {
        val mongoUri = env.getProperty("MONGODB_URI", "mongodb://localhost:27017")
        return MongoClients.create(mongoUri)
    }

    /**
     * Returns the name of the database to use.
     */
    override fun getDatabaseName(): String {
        return env.getProperty("MONGODB_DATABASE", "fomage")
    }
}
