package net.kigawa.fomage.web.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Controller for authentication-related pages.
 */
@Controller
class AuthController {

    /**
     * Shows the login page.
     */
    @GetMapping("/login")
    fun showLoginPage(
        @RequestParam(required = false) error: String?,
        @RequestParam(required = false) logout: String?,
        model: Model
    ): String {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password")
        }
        
        if (logout != null) {
            model.addAttribute("message", "You have been logged out")
        }
        
        return "auth/login"
    }
}