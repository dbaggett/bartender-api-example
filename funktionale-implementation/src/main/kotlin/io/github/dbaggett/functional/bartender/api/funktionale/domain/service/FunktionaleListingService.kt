package io.github.dbaggett.functional.bartender.api.funktionale.domain.service

import io.github.dbaggett.functional.bartender.api.common.domain.model.AlcoholType
import io.github.dbaggett.functional.bartender.api.common.domain.model.Listing
import io.github.dbaggett.functional.bartender.api.domain.service.InventoryService
import io.github.dbaggett.functional.bartender.api.domain.service.ListingService
import io.github.dbaggett.functional.bartender.api.domain.service.ServiceError
import kotlinx.coroutines.experimental.future.await
import kotlinx.coroutines.experimental.future.future
import org.funktionale.either.Disjunction
import org.funktionale.either.getOrElse
import org.funktionale.option.Option
import org.funktionale.option.toOption
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FunktionaleListingService @Inject constructor(val inventoryService: InventoryService) : ListingService {
    override fun getListing(alcoholType: AlcoholType?): CompletableFuture<Disjunction<ServiceError, Listing>> {
        return future {
            if (alcoholType == null) {
                Disjunction.right(
                        Listing(
                                beer = inventoryService.getAllBeers().await().getOrElse { emptyList() },
                                spirit = inventoryService.getAllSpirits().await().getOrElse { emptyList() },
                                wine = inventoryService.getAllWines().await().getOrElse { emptyList() }
                        )
                )
            } else {
                when (alcoholType) {
                    AlcoholType.beer -> inventoryService.getAllBeers().await().map { it -> Listing(beer = it) }
                    AlcoholType.spirit -> inventoryService.getAllSpirits().await().map { it -> Listing(spirit = it) }
                    AlcoholType.wine -> inventoryService.getAllWines().await().map { it -> Listing(wine = it) }
                }
            }
        }
    }
}