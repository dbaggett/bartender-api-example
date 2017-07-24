package io.github.dbaggett.functional.bartender.api.common.domain.model

data class Wine(
        override val id: String,
        val type: WineType,
        val style: WineStyle,
        override val alcoholByVolume: Float = 14F) : Alcohol