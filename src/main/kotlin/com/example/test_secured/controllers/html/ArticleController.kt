package com.example.test_secured.controllers.html

import com.example.test_secured.dtos.CreateArticle
import com.example.test_secured.services.ArticleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
@RequestMapping("/articles")
class ArticleController(private val service: ArticleService) {

    @GetMapping
    fun articles(model: Model): String {
        model["articles"] = service.listArticlesDtoByOrderByAddedAtDesc()
        return "articles"
    }

    @GetMapping("/{slug}")
    fun viewArticle(@PathVariable("slug") slug: String, model: Model): String {
        val article = service.findArticleDtoBySlug(slug)

        model["article"] = article
        return "article"
    }

    @GetMapping("/create")
    fun viewCreateArticleForm(model: Model): String {
        return "create-article"
    }

    @PostMapping("/create")
    fun createArticle(createArticle: CreateArticle, model: Model, principal: Principal): String {
        val article = service.createArticle(createArticle, principal.name)

        return "redirect:/articles/${article.slug}"
    }
}
