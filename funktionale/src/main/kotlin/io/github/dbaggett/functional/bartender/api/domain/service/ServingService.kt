package io.github.dbaggett.functional.bartender.api.domain.service

import io.github.dbaggett.functional.bartender.api.common.domain.model.Serving
import java.util.concurrent.CompletableFuture
import org.funktionale.either.Disjunction

interface ServingService {
    fun getServing(id: String): CompletableFuture<Disjunction<ServiceError, Serving>>
}