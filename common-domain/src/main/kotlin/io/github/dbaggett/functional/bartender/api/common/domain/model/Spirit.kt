package io.github.dbaggett.functional.bartender.api.common.domain.model

data class Spirit(
        override val id: String,
        val type: SpiritType,
        override val alcoholByVolume: Float,
        val proof: Float) : Alcohol