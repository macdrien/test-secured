package com.example.test_secured.dtos

data class ArticleDto(
    val slug: String,
    val title: String,
    val headline: String,
    val content: String,
    val author: UserDto,
    val addedAt: String,
)

data class CreateArticle(
    val title: String,
    val headline: String,
    val content: String,
    val authorLogin: String?,
)
