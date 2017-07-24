package io.github.dbaggett.functional.bartender.api.funktionale.domain.service

import io.github.dbaggett.functional.bartender.api.domain.service.InventoryService
import io.github.dbaggett.functional.bartender.api.common.domain.model.Alcohol
import io.github.dbaggett.functional.bartender.api.common.domain.model.Beer
import io.github.dbaggett.functional.bartender.api.common.domain.model.Spirit
import io.github.dbaggett.functional.bartender.api.common.domain.model.Wine
import io.github.dbaggett.functional.bartender.api.domain.repository.Repository
import io.github.dbaggett.functional.bartender.api.domain.service.DuplicateVerificationError
import io.github.dbaggett.functional.bartender.api.domain.service.EntityExistsServiceError
import io.github.dbaggett.functional.bartender.api.domain.service.ServiceError
import io.github.dbaggett.functional.bartender.api.utils.leftMap
import io.github.dbaggett.functional.bartender.api.utils.toServiceError
import io.github.vjames19.futures.jdk8.*
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.future.await
import kotlinx.coroutines.experimental.future.future
import org.funktionale.either.Disjunction
import org.funktionale.either.flatMap
import org.funktionale.validation.validate
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FunktionaleInventoryService @Inject constructor(
        val beerRepo: Repository<Beer>,
        val spiritRepo: Repository<Spirit>,
        val wineRepo: Repository<Wine>) : InventoryService {

    override fun getAllBeers(): CompletableFuture<Disjunction<ServiceError, List<Beer>>> {
        return beerRepo.getAlcohol().map { it.map { it.toList() }.leftMap { it.toServiceError() } }
    }

    override fun storeBeer(beer: Beer): CompletableFuture<Disjunction<ServiceError, Beer>> {
        return future {
            isStorable(getAllSpirits().await(), getAllWines().await(), beer)
                    .flatMap { runBlocking { beerRepo.addAlcohol(beer).await().leftMap { it.toServiceError() } } }
        }
    }

    override fun getAllSpirits(): CompletableFuture<Disjunction<ServiceError, List<Spirit>>> {
        return spiritRepo.getAlcohol().map { it.map { it.toList() }.leftMap { it.toServiceError() } }
    }

    override fun storeSpirit(spirit: Spirit): CompletableFuture<Disjunction<ServiceError, Spirit>> {
        return future {
            isStorable(getAllBeers().await(), getAllWines().await(), spirit)
                    .flatMap { runBlocking { spiritRepo.addAlcohol(spirit).await().leftMap { it.toServiceError() } } }
        }
    }

    override fun getAllWines(): CompletableFuture<Disjunction<ServiceError, List<Wine>>> {
        return wineRepo.getAlcohol().map { it.map { it.toList() }.leftMap { it.toServiceError() } }
    }

    override fun storeWine(wine: Wine): CompletableFuture<Disjunction<ServiceError, Wine>> {
        return future {
            isStorable(getAllBeers().await(), getAllSpirits().await(), wine)
                    .flatMap { runBlocking { wineRepo.addAlcohol(wine).await().leftMap { it.toServiceError() } } }
        }
    }

    private fun <T: Alcohol, R: Alcohol, S: Alcohol> isStorable(
            futureAlcoholEither1: Disjunction<ServiceError, List<T>>,
            futureAlcoholEither2: Disjunction<ServiceError, List<R>>,
            alcohol: S
    ): Disjunction<ServiceError, S> {
        val either1 = futureAlcoholEither1.map { it.map { it.id } }
        val either2 = futureAlcoholEither2.map { it.map { it.id } }

        val result = validate(either1, either2) {
            e1, e2 -> listOf(e1, e2).flatten()
        }.leftMap { DuplicateVerificationError }

        return when(result) {
            is Disjunction.Right -> {
                if (result.get().contains(alcohol.id)) {
                    Disjunction.left(EntityExistsServiceError(alcohol.id))
                } else {
                    Disjunction.right(alcohol)
                }
            }
            else -> result.map { _ -> alcohol }
        }
    }
}

