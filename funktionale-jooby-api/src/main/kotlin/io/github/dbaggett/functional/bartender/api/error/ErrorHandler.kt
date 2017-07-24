package io.github.dbaggett.functional.bartender.api.error

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.jooby.Request
import org.jooby.Response
import org.jooby.Status

interface ErrorHandler<in T: Exception> {
    fun handle(req: Request, response: Response, error: T): Unit
}

class JsonRequestErrorHandler : ErrorHandler<JsonMappingException> {
    override fun handle(req: Request, response: Response, error: JsonMappingException) = when(error) {
        is MissingKotlinParameterException -> response.sendError(ApiError(Status.BAD_REQUEST, error.message))
        else -> response.sendError(ApiError(Status.SERVER_ERROR, "Unexpected error has occurred: ${error.message}"))
    }
}