package com.camilo.mongographql.config

import com.camilo.mongographql.repositories.UserRepository
import com.camilo.mongographql.resolvers.UserMutation
import com.camilo.mongographql.resolvers.UserQuery
import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.directives.KotlinDirectiveWiringFactory
import com.expediagroup.graphql.hooks.SchemaGeneratorHooks
import com.expediagroup.graphql.toSchema
import graphql.schema.GraphQLSchema
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ConfigGraphQL(private val userRepository: UserRepository) {

    @Bean
    fun wiringFactory() = KotlinDirectiveWiringFactory()

    @Bean
    fun hooks(wiringFactory: KotlinDirectiveWiringFactory) = CustomSchemaGeneratorHooks(wiringFactory)

    @Bean
    fun schemaGeneratorConfig(hooks: SchemaGeneratorHooks): GraphQLSchema {


        val config = SchemaGeneratorConfig(supportedPackages = listOf("com.camilo"), hooks = hooks)


        val queries = listOf(TopLevelObject( UserQuery(userRepository) ))
        val mutations = listOf(TopLevelObject( UserMutation(userRepository) ))

        return toSchema(queries = queries, config = config, mutations = mutations )

    }
}