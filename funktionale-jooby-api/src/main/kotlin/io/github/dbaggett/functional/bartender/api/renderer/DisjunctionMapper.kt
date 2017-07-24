package io.github.dbaggett.functional.bartender.api.renderer

import io.github.dbaggett.functional.bartender.api.domain.service.EntityExistsServiceError
import io.github.dbaggett.functional.bartender.api.domain.service.EntityNotFound
import io.github.dbaggett.functional.bartender.api.error.ApiError
import io.github.vjames19.futures.jdk8.map
import org.funktionale.either.Disjunction
import org.jooby.Result
import org.jooby.Results
import org.jooby.Route
import java.util.concurrent.CompletableFuture

class DisjunctionMapper : Route.Mapper<Any> {

    override fun map(value: Any?): Any? {
        return when(value) {
            is CompletableFuture<*> -> map(value)
            else -> value
        }
    }

    private fun map(value: CompletableFuture<*>): CompletableFuture<Any>? {
        return value.map {result -> when(result) {
            is Disjunction<*, *> -> map(result)
            else -> result
        }}
    }

    private fun map(value: Disjunction<*, *>): Result {
        return when(value) {
            is Disjunction.Left -> {
                val leftValue = value.swap().get()

                when(leftValue) {
                    is EntityExistsServiceError -> ApiError(leftValue).toResult()
                    is EntityNotFound -> ApiError(leftValue).toResult()
                    else -> ApiError(500, "Unexpected error").toResult()
                }
            }
            is Disjunction.Right -> responseMap(value.get())
        }
    }

    private fun responseMap(value: Any?): Result {
        return when(value) {
            is Collection<*> -> CollectionResponse(200, value).toResult()
            else -> CollectionItemResponse(200, value).toResult()
        }
    }
}