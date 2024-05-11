package com.artemla.pokedex.domain.entities

data class PokemonSpeciesResponse(
    val evolution_chain: EvolutionUrl,
    val flavor_text_entries: List<FlavorTextEntry>
)

data class EvolutionUrl(
    val url: String
)

data class FlavorTextEntry(
    val flavor_text: String,
    val language: EntryLanguage
)

data class EntryLanguage(
    val name: String
)