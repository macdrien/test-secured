package com.example.test_secured.configuration

import com.example.test_secured.entities.Article
import com.example.test_secured.entities.Authority
import com.example.test_secured.entities.User
import com.example.test_secured.repository.ArticleRepository
import com.example.test_secured.repository.AuthorityRepository
import com.example.test_secured.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository, authorityRepository: AuthorityRepository, articleRepository: ArticleRepository) = ApplicationRunner {
        val userAuthority = authorityRepository.save(Authority("user"))
        userRepository.save(User("user", "Mike", "Nike", "{bcrypt}\$2a\$10\$JJxuauoqXm8L/qtQRatWcOIPvG8mp/dETDLTr/EwmOQhAh6x1Fx7S", authorities = listOf(userAuthority)))
        val john = userRepository.save(User("john.doe", "John", "Doe", "{bcrypt}\$2a\$10\$JJxuauoqXm8L/qtQRatWcOIPvG8mp/dETDLTr/EwmOQhAh6x1Fx7S", authorities = listOf(userAuthority)))
        articleRepository.save(Article(
            title = "Lorem title",
            headline = "Lorem",
            content = "dolor sit amet",
            author = john,
            id = UUID.randomUUID().toString(),
        ))
        articleRepository.save(Article(
            title = "Ipsum the strong-title",
            headline = "Ipsum",
            content = "dolor sit amet",
            author = john,
            id = UUID.randomUUID().toString(),
        ))
    }
}
