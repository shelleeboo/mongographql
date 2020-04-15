package com.camilo.mongographql.config


import com.expediagroup.graphql.directives.KotlinDirectiveWiringFactory
import com.expediagroup.graphql.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.schema.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KType


/**
 * Schema generator hook that adds additional scalar types.
 */
class CustomSchemaGeneratorHooks(override val wiringFactory: KotlinDirectiveWiringFactory) : SchemaGeneratorHooks {

    /**
     * Register additional GraphQL scalar types.
     */
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier) {
        UUID::class -> graphqlUUIDType
        HashMap::class -> graphqlHashMapType
        else -> null
    }

}

internal val graphqlUUIDType = GraphQLScalarType.newScalar()
        .name("UUID")
        .description("A type representing a formatted java.util.UUID")
        .coercing(UUIDCoercing)
        .build()

private object UUIDCoercing : Coercing<UUID, String> {
    override fun parseValue(input: Any?): UUID = UUID.fromString(
            serialize(
                    input
            )
    )

    override fun parseLiteral(input: Any?): UUID? {
        val uuidString = (input as? StringValue)?.value
        return UUID.fromString(uuidString)
    }

    override fun serialize(dataFetcherResult: Any?): String = dataFetcherResult.toString()
}


internal val graphqlHashMapType = GraphQLScalarType.newScalar()
        .name("HashMap")
        .description("A custom map scalar type")
        .coercing(HashMapCoercing)
        .build()


private object HashMapCoercing : Coercing<HashMap<String,String>, Any?> {
    @Throws(CoercingSerializeException::class)
    override fun serialize(dataFetcherResult: Any): Any? {
        var map: HashMap<*, *>? = null
        map = try {
            HashMap::class.java.cast(dataFetcherResult)
        } catch (exception: ClassCastException) {
            throw CoercingSerializeException("Could not convert $dataFetcherResult into a Map", exception)
        }
        return map
    }

    @Throws(CoercingParseValueException::class)
    override fun parseValue(input: Any): HashMap<String,String>? {
         return null
    }

    @Throws(CoercingParseLiteralException::class)
    override fun parseLiteral(input: Any): HashMap<String,String>? {
         return null
    }
}