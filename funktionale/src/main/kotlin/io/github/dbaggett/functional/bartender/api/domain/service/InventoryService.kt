package io.github.dbaggett.functional.bartender.api.domain.service

import io.github.dbaggett.functional.bartender.api.common.domain.model.Beer
import io.github.dbaggett.functional.bartender.api.common.domain.model.Spirit
import io.github.dbaggett.functional.bartender.api.common.domain.model.Wine
import java.util.concurrent.CompletableFuture
import org.funktionale.either.Disjunction

interface InventoryService {
    fun getAllBeers(): CompletableFuture<Disjunction<ServiceError, List<Beer>>>

    fun storeBeer(beer: Beer): CompletableFuture<Disjunction<ServiceError, Beer>>

    fun getAllSpirits(): CompletableFuture<Disjunction<ServiceError, List<Spirit>>>

    fun storeSpirit(spirit: Spirit): CompletableFuture<Disjunction<ServiceError, Spirit>>

    fun getAllWines(): CompletableFuture<Disjunction<ServiceError, List<Wine>>>

    fun storeWine(wine: Wine): CompletableFuture<Disjunction<ServiceError, Wine>>
}