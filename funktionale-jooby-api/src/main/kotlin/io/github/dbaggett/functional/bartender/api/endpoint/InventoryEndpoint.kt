package io.github.dbaggett.functional.bartender.api.endpoint

import io.github.dbaggett.functional.bartender.api.common.domain.model.*
import io.github.dbaggett.functional.bartender.api.domain.service.InventoryService
import io.github.dbaggett.functional.bartender.api.domain.service.ServiceError
import org.funktionale.either.Disjunction
import org.jooby.mvc.Body
import org.jooby.mvc.GET
import org.jooby.mvc.POST
import org.jooby.mvc.Path
import java.util.concurrent.CompletableFuture
import javax.inject.Inject


@Path("/inventory")
class InventoryEndpoint @Inject constructor(val inventoryService: InventoryService) {

    @GET
    @Path("/beers")
    fun getBeers(): CompletableFuture<Disjunction<ServiceError, List<Beer>>> {
        return inventoryService.getAllBeers()
    }

    @POST
    @Path("/beers")
    fun addBeer(@Body beer: AddBeerRequest): CompletableFuture<Disjunction<ServiceError, Beer>> {
        return inventoryService.storeBeer(
                Beer(beer.id, BeerType.valueOf(beer.type), BeerStyle.valueOf(beer.style), beer.alcoholByVolume)
        )
    }

    @GET
    @Path("/spirits")
    fun getSpirits(): CompletableFuture<Disjunction<ServiceError, List<Spirit>>> {
        return inventoryService.getAllSpirits()
    }

    @POST
    @Path("/spirits")
    fun addSpirit(@Body spirit: AddSpiritRequest): CompletableFuture<Disjunction<ServiceError, Spirit>> {
        return inventoryService.storeSpirit(
                Spirit(spirit.id, SpiritType.valueOf(spirit.type), spirit.alcoholByVolume, spirit.proof)
        )
    }

    @GET
    @Path("/wines")
    fun getWines(): CompletableFuture<Disjunction<ServiceError, List<Wine>>> {
        return inventoryService.getAllWines()
    }

    @POST
    @Path("/wines")
    fun addWine(@Body wine: AddWineRequest): CompletableFuture<Disjunction<ServiceError, Wine>> {
        return inventoryService.storeWine(
                Wine(wine.id, WineType.valueOf(wine.type), WineStyle.valueOf(wine.style), wine.alcoholByVolume)
        )
    }
}

data class AddBeerRequest(val id: String, val type: String, val style: String, val alcoholByVolume: Float)

data class AddSpiritRequest(val id: String, val type: String, val alcoholByVolume: Float, val proof: Float)

data class AddWineRequest(val id: String, val type: String, val style: String, val alcoholByVolume: Float)