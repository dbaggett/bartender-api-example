package io.github.dbaggett.functional.bartender.api.jooby

import io.restassured.response.*
import org.jooby.Status

inline fun <reified T> Response.As(): T {
    return this.`as`(T::class.java)
}

inline fun <reified T> ExtractableResponse<*>.As(): T {
    return this.`as`(T::class.java)
}

inline fun <reified T> ValidatableResponseOptions<*, *>.As(): T {
    return extract().As<T>()
}

fun <T : ValidatableResponseOptions<T, R>?, R> ValidatableResponseOptions<T, R>.statusCode(status: Status): T
        where R : ResponseOptions<R>, R : ResponseBody<R> {
    return statusCode(status.value())
}