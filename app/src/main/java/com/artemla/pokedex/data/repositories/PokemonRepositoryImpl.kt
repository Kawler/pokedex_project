package com.artemla.pokedex.data.repositories

import com.artemla.pokedex.domain.utils.RetrofitUtils
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonEvolution
import com.artemla.pokedex.domain.entities.PokemonListItem
import com.artemla.pokedex.domain.entities.PokemonSpeciesResponse
import com.artemla.pokedex.domain.repositories.PokemonRepository

object PokemonRepositoryImpl: PokemonRepository {
    private val retrofitUtils = RetrofitUtils

    override suspend fun fetchPokemonCount(): Int {
        return retrofitUtils.fetchPokemonCount()
    }

    override suspend fun fetchPokemonList(limit: Int, offset: Int): MutableList<PokemonListItem> {
        return retrofitUtils.fetchPokemonList(limit, offset)
    }

    override suspend fun fetchPokemonDetails(urls: List<String>): MutableList<PokemonDetailsResponse> {
        return retrofitUtils.fetchPokemonDetails(urls)
    }

    override suspend fun fetchSpecies(url: String): PokemonSpeciesResponse? {
        return retrofitUtils.fetchSpeciesData(url)
    }

    override suspend fun fetchEvolutions(url: String): List<PokemonEvolution> {
        return retrofitUtils.fetchEvolutionChain(url)
    }
}