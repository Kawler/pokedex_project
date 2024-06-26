package com.artemla.pokedex.ui.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemla.pokedex.data.repositories.PokemonRepositoryImpl
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import kotlinx.coroutines.launch

private const val BATCH_SIZE = 10

class MainActivityViewModel : ViewModel() {
    private val repository = PokemonRepositoryImpl
    private val _pokemonData = MutableLiveData<MutableList<PokemonDetailsResponse>>()
    val pokemonData: LiveData<MutableList<PokemonDetailsResponse>> get() = _pokemonData

    fun fetchDataFromApi() {
        viewModelScope.launch {
            try {
                val pokemonCount = repository.fetchPokemonCount()
                var offset = 0
                while (offset < pokemonCount) {
                    val limit =
                        if (offset + BATCH_SIZE <= pokemonCount) BATCH_SIZE else pokemonCount - offset
                    val batchPokemonList = repository.fetchPokemonList(limit, offset)
                    val batchPokemonData =
                        repository.fetchPokemonDetails(batchPokemonList.map { it.url })
                    _pokemonData.postValue(_pokemonData.value?.apply { addAll(batchPokemonData) }
                        ?: batchPokemonData.toMutableList())
                    offset += BATCH_SIZE
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}