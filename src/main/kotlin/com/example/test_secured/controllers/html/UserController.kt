package com.example.test_secured.controllers.html

import com.example.test_secured.dtos.RegisterUser
import com.example.test_secured.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/users")
class UserController(private val service: UserService) {
    @GetMapping("/login")
    fun viewLoginForm() = "login"

    @GetMapping("/register")
    fun viewRegisterForm() = "register"

    @PostMapping("/register")
    fun processRegisterForm(registerUser: RegisterUser): String {
        try {
            val user = service.registerUser(registerUser)
        } catch (exception: IllegalStateException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error during registration processing")
        }

        return "redirect:/login"
    }
}
