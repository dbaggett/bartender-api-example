package io.github.dbaggett.functional.bartender.api.utils

import org.funktionale.either.Disjunction

fun <X, L, R> Disjunction<L, R>.leftMap(f: (L) -> X): Disjunction<X, R> = when(this) {
    is Disjunction.Left -> Disjunction.Left(f(value))
    is Disjunction.Right -> Disjunction.Right(value)
}