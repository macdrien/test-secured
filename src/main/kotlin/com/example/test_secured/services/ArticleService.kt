package com.example.test_secured.services

import com.example.test_secured.dtos.CreateArticle
import com.example.test_secured.entities.Article
import com.example.test_secured.exceptions.NotFoundException
import com.example.test_secured.mappers.toArticle
import com.example.test_secured.mappers.toArticleDto
import com.example.test_secured.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService private constructor(private val repository: ArticleRepository, val userService: UserService) {

    fun listArticlesByOrderByAddedAtDesc() = repository.findAllByOrderByAddedAtDesc().toList()
    fun listArticlesDtoByOrderByAddedAtDesc() = listArticlesByOrderByAddedAtDesc().map { it.toArticleDto() }

    fun findArticleBySlug(slug: String) = repository.findBySlug(slug) ?: throw NotFoundException("Article $slug not found")
    fun findArticleDtoBySlug(slug: String) = findArticleBySlug(slug).toArticleDto()

    fun createArticle(createArticle: CreateArticle, authorName: String): Article {
        val author = userService.findUserByUsername(authorName)

        var article = createArticle.toArticle(author)
        article = repository.save(article)

        return article
    }
}
