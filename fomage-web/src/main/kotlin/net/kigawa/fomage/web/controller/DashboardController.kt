package net.kigawa.fomage.web.controller

import net.kigawa.fomage.web.service.ApiClientService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

/**
 * Controller for the dashboard page.
 */
@Controller
@RequestMapping("/dashboard")
class DashboardController(private val apiClientService: ApiClientService) {

    /**
     * Shows the dashboard page.
     */
    @GetMapping
    fun showDashboard(model: Model, principal: Principal): String {
        // Get statistics
        val databases = apiClientService.getDatabases()
        val totalBackups = databases.size
        
        // Get storage size (mock data for now)
        val storageSize = "1.2 GB"
        
        // Get latest backup date (mock data for now)
        val latestBackupDate = "2023-07-30 15:30:45"
        
        // Add data to model
        model.addAttribute("totalBackups", totalBackups)
        model.addAttribute("storageSize", storageSize)
        model.addAttribute("latestBackupDate", latestBackupDate)
        model.addAttribute("username", principal.name)
        
        return "dashboard/index"
    }
}