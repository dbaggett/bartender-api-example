package io.github.dbaggett.functional.bartender.api.endpoint

import io.github.dbaggett.functional.bartender.api.common.domain.model.AlcoholType
import io.github.dbaggett.functional.bartender.api.common.domain.model.Listing
import io.github.dbaggett.functional.bartender.api.domain.service.ListingService
import io.github.dbaggett.functional.bartender.api.domain.service.ServiceError
import org.funktionale.either.Disjunction
import org.jooby.mvc.GET
import org.jooby.mvc.Path
import java.util.*
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

@Path("/listing")
class ListingEndpoint @Inject constructor(val listingService: ListingService) {

    @GET
    fun getListing(alcoholType: Optional<AlcoholType>): CompletableFuture<Disjunction<ServiceError, Listing>> {
        return listingService.getListing(alcoholType.orElse(null))
    }
}