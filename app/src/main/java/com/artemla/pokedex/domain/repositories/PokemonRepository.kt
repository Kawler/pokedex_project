package com.artemla.pokedex.domain.repositories

import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonEvolution
import com.artemla.pokedex.domain.entities.PokemonListItem
import com.artemla.pokedex.domain.entities.PokemonSpeciesResponse

interface PokemonRepository {
    suspend fun fetchPokemonCount(): Int
    suspend fun fetchPokemonList(limit: Int, offset: Int): MutableList<PokemonListItem>
    suspend fun fetchPokemonDetails(urls: List<String>): MutableList<PokemonDetailsResponse>
    suspend fun fetchSpecies(url: String): PokemonSpeciesResponse?
    suspend fun fetchEvolutions(url: String): List<PokemonEvolution>
}
