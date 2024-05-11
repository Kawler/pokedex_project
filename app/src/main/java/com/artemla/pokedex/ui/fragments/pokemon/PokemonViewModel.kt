package com.artemla.pokedex.ui.fragments.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemla.pokedex.data.repositories.PokemonRepository
import com.artemla.pokedex.domain.entities.EvolutionSpecies
import com.artemla.pokedex.domain.entities.PokemonEvolution
import com.artemla.pokedex.domain.entities.PokemonSpeciesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepository
    private lateinit var species: PokemonSpeciesResponse
    fun getDescription(url: String): String {
        var result = ""
        runBlocking {
            species = repository.fetchSpecies(url)!!
            for (entry in species.flavor_text_entries) {
                if (entry.language.name == "en") {
                    result = entry.flavor_text.replace("\n", " ")
                }
            }
        }
        return result
    }

    fun getEvolutions(url: String): List<PokemonEvolution>{
        var result: List<PokemonEvolution> = listOf()
        runBlocking {
            result = repository.fetchEvolutions(species.evolution_chain.url)
        }
        return result
    }
}