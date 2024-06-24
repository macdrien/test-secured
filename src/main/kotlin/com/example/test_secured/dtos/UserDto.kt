package com.example.test_secured.dtos

data class UserDto(
    val username: String,
    val givenname: String,
    val familyname: String,
)

data class RegisterUser(
    val username: String,
    val givenname: String,
    val familyname: String,
    val password: String,
)
