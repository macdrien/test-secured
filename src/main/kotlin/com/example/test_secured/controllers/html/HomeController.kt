package com.example.test_secured.controllers.html

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("")
    fun index() = "index"
}
