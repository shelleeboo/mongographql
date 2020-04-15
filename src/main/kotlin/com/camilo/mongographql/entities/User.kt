package com.camilo.mongographql.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class User(
    @Id
    var id: UUID? = null,
    val name: String? = null
)
