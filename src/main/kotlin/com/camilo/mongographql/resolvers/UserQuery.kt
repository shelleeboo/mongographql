package com.camilo.mongographql.resolvers

import com.camilo.mongographql.entities.User
import com.camilo.mongographql.repositories.UserRepository

import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.collections.HashMap

@Component
class UserQuery(
    private val userRepository: UserRepository
) {

    fun users()  = userRepository.findAll()


    }
