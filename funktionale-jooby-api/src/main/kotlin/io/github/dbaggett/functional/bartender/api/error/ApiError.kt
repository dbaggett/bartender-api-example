package io.github.dbaggett.functional.bartender.api.error

import io.github.dbaggett.functional.bartender.api.domain.service.EntityExistsServiceError
import io.github.dbaggett.functional.bartender.api.domain.service.EntityNotFound
import org.jooby.Result
import org.jooby.Results
import org.jooby.Status

data class ApiError(val status: Int, val message: String?) {
    constructor(error: EntityExistsServiceError) : this(409, "Beverage already exists with id '${error.id}'")

    constructor(error: EntityNotFound) : this(404, "Beverage with id '${error.id}' does not exist")

    constructor(status: Status, message: String?) : this(status.value(), message)

    fun toResult(): Result = Results.with(this, status).type("application/json")
}