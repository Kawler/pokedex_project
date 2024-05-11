package com.artemla.pokedex.domain.entities

data class PokemonTypeListItem (
    val pokemonType: PokemonType,
    val color: String,
    val textColor: String
)

enum class PokemonType {
    NORMAL,
    FIRE,
    WATER,
    ELECTRIC,
    GRASS,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DRAGON,
    DARK,
    STEEL,
    FAIRY,
    ALL,
    NEW
}