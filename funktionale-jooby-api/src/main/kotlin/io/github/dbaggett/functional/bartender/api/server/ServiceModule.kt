package io.github.dbaggett.functional.bartender.api.server

import io.github.dbaggett.functional.bartender.api.domain.service.InventoryService
import io.github.dbaggett.functional.bartender.api.domain.service.ListingService
import io.github.dbaggett.functional.bartender.api.domain.service.ServingService
import io.github.dbaggett.functional.bartender.api.funktionale.domain.repository.BeerInMemoryRepository
import io.github.dbaggett.functional.bartender.api.funktionale.domain.repository.SpiritInMemoryRepository
import io.github.dbaggett.functional.bartender.api.funktionale.domain.repository.WineInMemoryRepository
import io.github.dbaggett.functional.bartender.api.funktionale.domain.service.FunktionaleInventoryService
import io.github.dbaggett.functional.bartender.api.funktionale.domain.service.FunktionaleListingService
import io.github.dbaggett.functional.bartender.api.funktionale.domain.service.FunktionaleServingService
import com.google.inject.TypeLiteral
import io.github.dbaggett.functional.bartender.api.common.domain.model.Beer
import io.github.dbaggett.functional.bartender.api.common.domain.model.Spirit
import io.github.dbaggett.functional.bartender.api.common.domain.model.Wine
import io.github.dbaggett.functional.bartender.api.domain.repository.Repository

object ServiceModule : Module() {

    override fun configure() {
        bind(object : TypeLiteral<Repository<Beer>>() {}).to<BeerInMemoryRepository>()
        bind(object : TypeLiteral<Repository<Spirit>>() {}).to<SpiritInMemoryRepository>()
        bind(object : TypeLiteral<Repository<Wine>>() {}).to<WineInMemoryRepository>()
        bind<InventoryService>().to<FunktionaleInventoryService>()
        bind<ListingService>().to<FunktionaleListingService>()
        bind<ServingService>().to<FunktionaleServingService>()
    }
}