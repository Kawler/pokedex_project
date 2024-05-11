package com.artemla.pokedex.domain.entities

import com.google.gson.annotations.SerializedName

data class PokemonEvolution(val name: String, val url: String)
data class PokemonSpecies(
    val chain: EvolutionChain?
)

data class EvolutionChain(
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>?,
    val species: EvolutionSpecies
)

data class EvolvesTo(
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>,
    val species: EvolutionSpecies
)

data class EvolutionSpecies(
    val name: String,
    val url: String
)