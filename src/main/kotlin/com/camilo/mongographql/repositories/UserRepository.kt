package com.camilo.mongographql.repositories

import com.camilo.mongographql.entities.User
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: MongoRepository<User, UUID>
