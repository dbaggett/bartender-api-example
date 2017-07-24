package io.github.dbaggett.functional.bartender.api.renderer

import org.jooby.Result
import org.jooby.Results

data class CollectionItemResponse(val status: Int, val result: Any?) {
    fun toResult(): Result = Results.with(this, status).type("application/json")
}