package com.camilo.mongographql

import com.camilo.mongographql.config.CustomSchemaGeneratorHooks
import com.camilo.mongographql.resolvers.UserMutation
import com.camilo.mongographql.resolvers.UserQuery
import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.directives.KotlinDirectiveWiringFactory
import com.expediagroup.graphql.hooks.SchemaGeneratorHooks
import com.expediagroup.graphql.toSchema
import graphql.schema.GraphQLSchema
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
