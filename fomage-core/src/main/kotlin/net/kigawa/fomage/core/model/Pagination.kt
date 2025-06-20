package net.kigawa.fomage.core.model

/**
 * Pagination information model.
 * 
 * Fields:
 * - page: Current page number
 * - size: Number of items per page
 * - total: Total number of items
 * - totalPages: Total number of pages
 */
class Pagination {
    var page: Int = 0
    var size: Int = 20
    var total: Int = 0
    var totalPages: Int = 0
    
    /**
     * Calculates the total number of pages based on the total number of items and page size.
     */
    fun calculateTotalPages() {
        totalPages = if (size > 0) (total + size - 1) / size else 0
    }
}