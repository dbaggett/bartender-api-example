package io.github.dbaggett.functional.bartender.api.funktionale.domain.repository

import io.github.dbaggett.functional.bartender.api.common.domain.model.Spirit
import io.github.dbaggett.functional.bartender.api.funktionale.core.InMemoryRepository
import javax.inject.Singleton

@Singleton
class SpiritInMemoryRepository : InMemoryRepository<Spirit>()