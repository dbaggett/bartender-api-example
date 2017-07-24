package io.github.dbaggett.functional.bartender.api.endpoint

import io.github.dbaggett.functional.bartender.api.common.domain.model.Serving
import io.github.dbaggett.functional.bartender.api.domain.service.ServiceError
import io.github.dbaggett.functional.bartender.api.domain.service.ServingService
import org.funktionale.either.Disjunction
import org.jooby.mvc.GET
import org.jooby.mvc.Path
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

@Path("/serving")
class ServingEndpoint @Inject constructor(val servingService: ServingService) {

    @GET
    fun getServing(id: String): CompletableFuture<Disjunction<ServiceError, Serving>> {
        return servingService.getServing(id)
    }
}