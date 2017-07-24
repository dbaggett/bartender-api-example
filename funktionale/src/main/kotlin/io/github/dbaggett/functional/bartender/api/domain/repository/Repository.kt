package io.github.dbaggett.functional.bartender.api.domain.repository

import io.github.dbaggett.functional.bartender.api.common.domain.model.Alcohol
import org.funktionale.either.Disjunction
import java.util.concurrent.CompletableFuture

interface Repository<T: Alcohol> {
    fun getAlcohol(): CompletableFuture<Disjunction<RepositoryError, Set<T>>>

    fun addAlcohol(alcohol: T): CompletableFuture<Disjunction<RepositoryError, T>>
}