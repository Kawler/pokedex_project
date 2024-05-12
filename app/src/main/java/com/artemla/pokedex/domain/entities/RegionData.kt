package com.artemla.pokedex.domain.entities

data class Region(
    val name: PokemonRegion
)

enum class PokemonRegion {
    KANTO,
    JOHTO,
    HOENN,
    SINNOH,
    UNOVA,
    KALOS,
    ALOLA,
    GALAR
}