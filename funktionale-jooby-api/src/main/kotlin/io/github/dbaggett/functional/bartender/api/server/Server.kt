package io.github.dbaggett.functional.bartender.api.server

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.dbaggett.functional.bartender.api.endpoint.InventoryEndpoint
import io.github.dbaggett.functional.bartender.api.endpoint.ListingEndpoint
import io.github.dbaggett.functional.bartender.api.endpoint.ServingEndpoint
import io.github.dbaggett.functional.bartender.api.error.JsonRequestErrorHandler
import io.github.dbaggett.functional.bartender.api.renderer.DisjunctionMapper
import org.jooby.*
import org.jooby.json.Jackson

fun app(): Jooby {
    return jooby {
        use(ServiceModule)
        use(Jackson().module(KotlinModule()))

        map(AsyncMapper())
        map(DisjunctionMapper())

        use(InventoryEndpoint::class)
        use(ListingEndpoint::class)
        use(ServingEndpoint::class)

        err(JsonMappingException::class.java) {req: Request, resp: Response, err: Err ->
            JsonRequestErrorHandler().handle(req, resp, err.cause!! as JsonMappingException)
        }
    }
}

fun main(args: Array<String>) {
    Jooby.run(::app, args)
}