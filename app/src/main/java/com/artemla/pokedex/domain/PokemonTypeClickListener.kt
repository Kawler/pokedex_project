package com.artemla.pokedex.domain


import com.artemla.pokedex.domain.entities.PokemonTypeListItem

interface PokemonTypeClickListener {
    fun onPokemonTypeClicked(pokemonTypeListItem: PokemonTypeListItem)
}