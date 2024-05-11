package com.artemla.pokedex.data.singletons

import android.util.Log
import com.artemla.pokedex.domain.EvolutionService
import com.artemla.pokedex.domain.PokemonCountService
import com.artemla.pokedex.domain.PokemonDetailsService
import com.artemla.pokedex.domain.PokemonListService
import com.artemla.pokedex.domain.PokemonSpeciesService
import com.artemla.pokedex.domain.entities.EvolvesTo
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonEvolution
import com.artemla.pokedex.domain.entities.PokemonListItem
import com.artemla.pokedex.domain.entities.PokemonSpeciesResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitUtils {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private const val TIMEOUT_DURATION_SECONDS = 60L

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
        .build()

    // Create a Retrofit instance with the custom OkHttpClient
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient) // Set the custom OkHttpClient
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val pokemonCountService: PokemonCountService by lazy {
        retrofit.create(PokemonCountService::class.java)
    }

    private val pokemonListService: PokemonListService by lazy {
        retrofit.create(PokemonListService::class.java)
    }

    private val pokemonDetailsService: PokemonDetailsService by lazy {
        retrofit.create(PokemonDetailsService::class.java)
    }

    private val pokemonEvolutionService: EvolutionService by lazy {
        retrofit.create(EvolutionService::class.java)
    }

    private val pokemonSpeciesService: PokemonSpeciesService by lazy {
        retrofit.create(PokemonSpeciesService::class.java)
    }

    suspend fun fetchPokemonCount(): Int {
        return try {
            val response = pokemonCountService.getPokemonCount()
            response.count
        } catch (e: Exception) {
            // Handle network or other errors
            0
        }
    }

    suspend fun fetchPokemonList(limit: Int,offset: Int): MutableList<PokemonListItem> {
        return try {
            val url = "https://pokeapi.co/api/v2/pokemon/?limit=$limit&offset=$offset"
            val response = pokemonListService.getPokemonList(url)
            response.results
        } catch (e: Exception) {
            // Handle network or other errors
            emptyList<PokemonListItem>()
        }.toMutableList()
    }

    suspend fun fetchPokemonDetails(urls: List<String>): MutableList<PokemonDetailsResponse> {
        val pokemonDetailsList = mutableListOf<PokemonDetailsResponse>()
        try {
            for (url in urls) {
                val response = pokemonDetailsService.getPokemonDetails(url)
                pokemonDetailsList.add(response)
            }
        } catch (e: Exception) {
            Log.i("EEEError",e.toString())
        }
        return pokemonDetailsList
    }

    suspend fun fetchSpeciesData(url: String): PokemonSpeciesResponse? {
        var speciesData: PokemonSpeciesResponse? = null
        try {
            val response = pokemonSpeciesService.getSpeciesDetails(url)
            speciesData = response
        } catch (e: Exception) {
            Log.i("EEEError",e.toString())
        }
        return speciesData
    }

    suspend fun fetchEvolutionChain(url: String): List<PokemonEvolution> {
        val response = pokemonEvolutionService.getEvolutionChain(url)
        val evolutionList = mutableListOf<PokemonEvolution>()
        if (response.chain?.evolvesTo != null) {
            evolutionList.add(PokemonEvolution(response.chain.species.name, response.chain.species.url))
        } else {
            println(evolutionList)
            return evolutionList
        }

        fun traverseEvolutionChain(evolvesTo: List<EvolvesTo>) {
            for (evolution in evolvesTo) {
                evolutionList.add(PokemonEvolution(evolution.species.name, evolution.species.url))
                traverseEvolutionChain(evolution.evolvesTo)
            }
        }

            traverseEvolutionChain(response.chain.evolvesTo)
        println(evolutionList)
        return evolutionList
    }
}