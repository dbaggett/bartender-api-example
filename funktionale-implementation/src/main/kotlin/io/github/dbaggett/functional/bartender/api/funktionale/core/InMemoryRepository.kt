package io.github.dbaggett.functional.bartender.api.funktionale.core

import io.github.dbaggett.functional.bartender.api.common.domain.model.Alcohol
import io.github.dbaggett.functional.bartender.api.domain.repository.EntityExistsRepositoryError
import io.github.dbaggett.functional.bartender.api.domain.repository.Repository
import io.github.dbaggett.functional.bartender.api.domain.repository.RepositoryError
import io.github.vjames19.futures.jdk8.ImmediateFuture
import org.funktionale.either.Disjunction
import java.util.concurrent.CompletableFuture

abstract class InMemoryRepository<T: Alcohol> : Repository<T> {
    private val entities = hashMapOf<String, T>()

    override fun getAlcohol(): CompletableFuture<Disjunction<RepositoryError, Set<T>>> {
        return ImmediateFuture { Disjunction.right(entities.values.toSet()) }
    }

    override fun addAlcohol(alcohol: T): CompletableFuture<Disjunction<RepositoryError, T>> {
        when(entities[alcohol.id]) {
            is Alcohol -> return ImmediateFuture { Disjunction.left(EntityExistsRepositoryError(alcohol.id)) }
            else -> {
                entities.put(alcohol.id, alcohol)
                return ImmediateFuture { Disjunction.right(alcohol) }
            }
        }
    }
}