package io.github.dbaggett.functional.bartender.api.domain.service

import io.github.dbaggett.functional.bartender.api.common.domain.model.AlcoholType
import io.github.dbaggett.functional.bartender.api.common.domain.model.Listing
import java.util.concurrent.CompletableFuture
import org.funktionale.either.Disjunction

interface ListingService {
    fun getListing(alcoholType: AlcoholType?): CompletableFuture<Disjunction<ServiceError, Listing>>
}