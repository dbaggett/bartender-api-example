package io.github.dbaggett.functional.bartender.api.error

import org.jooby.Response

fun Response.sendError(error: ApiError) {
    send(error.toResult())
}