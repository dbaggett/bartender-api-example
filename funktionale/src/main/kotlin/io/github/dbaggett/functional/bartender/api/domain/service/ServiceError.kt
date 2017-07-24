package io.github.dbaggett.functional.bartender.api.domain.service

sealed class ServiceError
data class EntityExistsServiceError(val id: String) : ServiceError()
data class EntityNotFound(val id: String) : ServiceError()
object DuplicateVerificationError : ServiceError()
object InventoryRetrievalError : ServiceError()