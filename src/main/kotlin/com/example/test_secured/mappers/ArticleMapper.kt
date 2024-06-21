package com.example.test_secured.mappers

import com.example.test_secured.configuration.format
import com.example.test_secured.dtos.ArticleDto
import com.example.test_secured.dtos.CreateArticle
import com.example.test_secured.entities.Article
import com.example.test_secured.entities.User
import java.util.*

fun Article.toArticleDto() = ArticleDto(
    slug,
    title,
    headline,
    content,
    author.toUserDto(),
    addedAt.format()
)

fun CreateArticle.toArticle(user: User, id: String? = null) = Article(
    title,
    headline,
    content,
    user,
    id = id ?: UUID.randomUUID().toString(),
)
