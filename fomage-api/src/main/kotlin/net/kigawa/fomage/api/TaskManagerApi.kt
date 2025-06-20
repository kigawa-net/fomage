package net.kigawa.fomage.api

/**
 * API interface for automated operational tasks.
 */
interface TaskManagerApi {
    /**
     * Schedules a data cleanup task.
     */
    fun scheduleCleanup(database: String, collection: String, olderThan: Int): String

    /**
     * Creates indexes for a collection.
     */
    fun createIndexes(database: String, collection: String, fields: List<String>): List<String>
}