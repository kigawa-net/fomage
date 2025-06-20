package net.kigawa.fomage.api

/**
 * API interface for database performance monitoring and alerting.
 */
interface MonitoringManagerApi {
    /**
     * Gets performance metrics for a database.
     */
    fun getMetrics(database: String): Map<String, Double>

    /**
     * Sets up an alert for a metric.
     */
    fun setupAlert(metric: String, threshold: Double): String
}