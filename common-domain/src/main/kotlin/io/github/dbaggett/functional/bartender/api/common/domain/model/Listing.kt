package io.github.dbaggett.functional.bartender.api.common.domain.model

data class Listing(
        val beer: List<Beer> = emptyList(),
        val spirit: List<Spirit> = emptyList(),
        val wine: List<Wine> = emptyList()
)