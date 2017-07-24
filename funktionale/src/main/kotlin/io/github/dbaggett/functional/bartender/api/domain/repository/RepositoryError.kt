package io.github.dbaggett.functional.bartender.api.domain.repository

sealed class RepositoryError
data class EntityExistsRepositoryError(val id: String) : RepositoryError()