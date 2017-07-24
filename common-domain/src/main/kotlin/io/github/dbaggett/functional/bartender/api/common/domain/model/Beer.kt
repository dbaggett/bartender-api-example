package io.github.dbaggett.functional.bartender.api.common.domain.model

data class Beer(
        override val id: String,
        val type: BeerType,
        val style: BeerStyle,
        override val alcoholByVolume: Float) : Alcohol