package com.artemla.pokedex.domain

import com.artemla.pokedex.domain.entities.PokemonCountResponse
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonListResponse
import com.artemla.pokedex.domain.entities.PokemonSpecies
import com.artemla.pokedex.domain.entities.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonCountService {
    @GET("pokemon/?limit=1")
    suspend fun getPokemonCount(): PokemonCountResponse
}

interface PokemonDetailsService {
    @GET
    suspend fun getPokemonDetails(@Url url: String): PokemonDetailsResponse
}

interface PokemonListService {
    @GET
    suspend fun getPokemonList(@Url url: String): PokemonListResponse
}

interface PokemonSpeciesService{
    @GET
    suspend fun getSpeciesDetails(@Url url: String): PokemonSpeciesResponse
}

interface EvolutionService {
    @GET
    suspend fun getEvolutionChain(@Url url: String): PokemonSpecies
}