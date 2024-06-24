package com.example.test_secured.mappers

import com.example.test_secured.dtos.RegisterUser
import com.example.test_secured.dtos.UserDto
import com.example.test_secured.entities.Authority
import com.example.test_secured.entities.User

fun User.toUserDto() = UserDto(
    username,
    givenname,
    familyname,
)

fun RegisterUser.toUser(id: String,
                        encodedPassword: String? = null,
                        authorities: List<Authority>,
                        enabled: Boolean = true) =
    User(
        id = id,
        username = username,
        givenname = givenname,
        familyname = familyname,
        password = encodedPassword ?: password,
        authorities = authorities,
        enabled = enabled,
    )
