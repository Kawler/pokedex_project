package com.artemla.pokedex.data.utils

import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.domain.entities.PokemonTypeListItem

object PokemonTypeUtils {
    val pokemonTypeList: List<PokemonTypeListItem> = listOf(
        PokemonTypeListItem(PokemonType.ALL, "#000000", "#FFFFFF"),
        PokemonTypeListItem(PokemonType.NORMAL, "#808080", "#000000"),   // Normal
        PokemonTypeListItem(PokemonType.FIRE, "#F08030", "#000000"),     // Fire
        PokemonTypeListItem(PokemonType.WATER, "#6890F0", "#000000"),    // Water
        PokemonTypeListItem(PokemonType.ELECTRIC, "#F8D030", "#000000"), // Electric
        PokemonTypeListItem(PokemonType.GRASS, "#78C850", "#000000"),    // Grass
        PokemonTypeListItem(PokemonType.ICE, "#98D8D8", "#000000"),       // Ice
        PokemonTypeListItem(PokemonType.FIGHTING, "#C03028", "#FFFFFF"), // Fighting
        PokemonTypeListItem(PokemonType.POISON, "#A040A0", "#000000"),   // Poison
        PokemonTypeListItem(PokemonType.GROUND, "#E0C068", "#000000"),   // Ground
        PokemonTypeListItem(PokemonType.FLYING, "#A890F0", "#000000"),   // Flying
        PokemonTypeListItem(PokemonType.PSYCHIC, "#F85888", "#000000"),  // Psychic
        PokemonTypeListItem(PokemonType.BUG, "#A8B820", "#000000"),      // Bug
        PokemonTypeListItem(PokemonType.ROCK, "#B8A038", "#000000"),     // Rock
        PokemonTypeListItem(PokemonType.GHOST, "#705898", "#FFFFFF"),    // Ghost
        PokemonTypeListItem(PokemonType.DRAGON, "#7038F8", "#FFFFFF"),   // Dragon
        PokemonTypeListItem(PokemonType.DARK, "#705848", "#FFFFFF"),     // Dark
        PokemonTypeListItem(PokemonType.STEEL, "#B8B8D0", "#000000"),    // Steel
        PokemonTypeListItem(PokemonType.FAIRY, "#EE99AC", "#000000")     // Fairy
    )
}