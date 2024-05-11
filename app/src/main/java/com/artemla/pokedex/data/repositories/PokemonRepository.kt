package com.artemla.pokedex.data.repositories

import com.artemla.pokedex.data.singletons.RetrofitUtils
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonEvolution
import com.artemla.pokedex.domain.entities.PokemonListItem
import com.artemla.pokedex.domain.entities.PokemonSpeciesResponse

object PokemonRepository {
    private val retrofitUtils = RetrofitUtils

    suspend fun fetchPokemonCount(): Int {
        return retrofitUtils.fetchPokemonCount()
    }

    suspend fun fetchPokemonList(limit: Int, offset: Int): MutableList<PokemonListItem> {
        return retrofitUtils.fetchPokemonList(limit, offset)
    }

    suspend fun fetchPokemonDetails(urls: List<String>): MutableList<PokemonDetailsResponse> {
        return retrofitUtils.fetchPokemonDetails(urls)
    }

    suspend fun fetchSpecies(url: String): PokemonSpeciesResponse? {
        return retrofitUtils.fetchSpeciesData(url)
    }

    suspend fun fetchEvolutions(url: String): List<PokemonEvolution>{
        return retrofitUtils.fetchEvolutionChain(url)
    }
}