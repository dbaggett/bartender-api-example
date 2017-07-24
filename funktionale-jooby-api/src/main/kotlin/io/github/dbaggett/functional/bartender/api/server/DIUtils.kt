package io.github.dbaggett.functional.bartender.api.server

import com.google.inject.Binder
import com.google.inject.binder.AnnotatedBindingBuilder
import com.google.inject.binder.ScopedBindingBuilder

inline fun <reified T> Module.bind(): AnnotatedBindingBuilder<T> {
    return binder().bind<T>()
}

inline fun <reified T> Binder.bind() = bind(T::class.java)

inline fun <reified T> AnnotatedBindingBuilder<in T>.to(): ScopedBindingBuilder = to(T::class.java)