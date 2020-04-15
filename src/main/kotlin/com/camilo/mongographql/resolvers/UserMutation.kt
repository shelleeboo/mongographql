package com.camilo.mongographql.resolvers

import com.camilo.mongographql.entities.User
import com.camilo.mongographql.repositories.UserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserMutation(
    private val userRepository: UserRepository
)  {

     fun create(name: String): User {
         val user = User(id = UUID.randomUUID(), name = name)
         userRepository.insert(user)
         return user
     }

}
