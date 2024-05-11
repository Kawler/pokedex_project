package com.artemla.pokedex.domain.entities

data class PokemonListResponse(
    val results: MutableList<PokemonListItem>
)