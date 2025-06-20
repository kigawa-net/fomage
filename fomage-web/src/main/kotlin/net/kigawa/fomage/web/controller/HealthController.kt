package net.kigawa.fomage.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller for health check endpoint.
 */
@RestController
class HealthController {

    /**
     * Health check endpoint.
     * Returns 200 OK if the application is running.
     */
    @GetMapping("/health")
    fun healthCheck(): ResponseEntity<Map<String, String>> {
        val response = mapOf("status" to "UP")
        return ResponseEntity.ok(response)
    }
}