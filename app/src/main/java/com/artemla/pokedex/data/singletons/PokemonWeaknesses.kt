package com.artemla.pokedex.data.singletons

import com.artemla.pokedex.domain.entities.PokemonType

object PokemonWeaknesses {
    fun getWeaknesses(type: PokemonType): Set<String> {
        return when (type) {
            PokemonType.NORMAL -> setOf("Fighting")
            PokemonType.FIRE -> setOf("Water", "Rock", "Ground")
            PokemonType.WATER -> setOf("Electric", "Grass")
            PokemonType.ELECTRIC -> setOf("Ground")
            PokemonType.GRASS -> setOf("Fire", "Ice", "Flying", "Bug", "Poison")
            PokemonType.ICE -> setOf("Fire", "Fighting", "Rock", "Steel")
            PokemonType.FIGHTING -> setOf("Flying", "Psychic", "Fairy")
            PokemonType.POISON -> setOf("Ground", "Psychic")
            PokemonType.GROUND -> setOf("Water", "Grass", "Ice")
            PokemonType.FLYING -> setOf("Electric", "Ice", "Rock")
            PokemonType.PSYCHIC -> setOf("Bug", "Ghost", "Dark")
            PokemonType.BUG -> setOf("Fire", "Flying", "Rock")
            PokemonType.ROCK -> setOf("Water", "Grass", "Fighting", "Ground", "Steel")
            PokemonType.GHOST -> setOf("Ghost", "Dark")
            PokemonType.DRAGON -> setOf("Ice", "Dragon", "Fairy")
            PokemonType.DARK -> setOf("Fighting", "Bug", "Fairy")
            PokemonType.STEEL -> setOf("Fire", "Fighting", "Ground")
            PokemonType.FAIRY -> setOf("Poison", "Steel")
            else -> emptySet()
        }
    }
}