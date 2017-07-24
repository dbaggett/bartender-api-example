package io.github.dbaggett.functional.bartender.api.utils

import io.github.dbaggett.functional.bartender.api.domain.repository.EntityExistsRepositoryError
import io.github.dbaggett.functional.bartender.api.domain.repository.RepositoryError
import io.github.dbaggett.functional.bartender.api.domain.service.EntityExistsServiceError
import io.github.dbaggett.functional.bartender.api.domain.service.ServiceError

fun RepositoryError.toServiceError(): ServiceError = when(this) {
    is EntityExistsRepositoryError -> EntityExistsServiceError(this.id)
}