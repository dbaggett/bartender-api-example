package io.github.dbaggett.functional.bartender.api.funktionale.domain.service

import io.github.dbaggett.functional.bartender.api.common.domain.model.Serving
import io.github.dbaggett.functional.bartender.api.domain.service.*
import io.github.dbaggett.functional.bartender.api.utils.leftMap
import kotlinx.coroutines.experimental.future.await
import kotlinx.coroutines.experimental.future.future
import org.funktionale.either.Disjunction
import org.funktionale.validation.validate
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

class FunktionaleServingService @Inject constructor(val inventoryService: InventoryService) : ServingService {
    override fun getServing(id: String): CompletableFuture<Disjunction<ServiceError, Serving>> {
        return future {
            val result = validate(
                    inventoryService.getAllBeers().await().map { it.map { it.id } },
                    inventoryService.getAllSpirits().await().map { it.map { it.id } },
                    inventoryService.getAllWines().await().map { it.map { it.id } }
            ) { beerIds, spiritIds, wineIds ->
                listOf(beerIds, spiritIds, wineIds).flatten()
            }.leftMap { InventoryRetrievalError }

            when(result) {
                is Disjunction.Right -> {
                    if (!result.get().contains(id)) {
                        Disjunction.left(EntityNotFound(id))
                    } else {
                        Disjunction.right(Serving(id))
                    }
                }
                else -> result.map { _ -> Serving(id) }
            }
        }
    }
}