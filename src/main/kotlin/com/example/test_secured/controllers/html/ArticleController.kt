package com.example.test_secured.controllers.html

import com.example.test_secured.configuration.toSlug
import com.example.test_secured.dtos.CreateArticle
import com.example.test_secured.mappers.toArticle
import com.example.test_secured.mappers.toArticleDto
import com.example.test_secured.repository.ArticleRepository
import com.example.test_secured.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException
import java.security.Principal

@Controller
@RequestMapping("/articles")
class ArticleController(private val repository: ArticleRepository, private val userRepository: UserRepository) {

    @GetMapping
    fun articles(model: Model): String {
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.toArticleDto() }
        return "articles"
    }

    @GetMapping("/{slug}")
    fun viewArticle(@PathVariable("slug") slug: String, model: Model): String {
        val article = repository.findBySlug(slug)
            ?.toArticleDto()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Article '$slug' not found")

        model["article"] = article
        return "article"
    }

    @GetMapping("/create")
    fun viewCreateArticleForm(model: Model): String {
        return "create-article"
    }

    @PostMapping("/create")
    fun createArticle(createArticle: CreateArticle, model: Model, principal: Principal): String {
        val author = userRepository.findByUsername(principal.name)
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED)

        val article = createArticle.toArticle(author)
        repository.save(article)

        return "redirect:/articles/${article.title.toSlug()}"
    }
}
