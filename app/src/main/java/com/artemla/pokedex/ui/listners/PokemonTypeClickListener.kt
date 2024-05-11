package com.artemla.pokedex.ui.listners


import com.artemla.pokedex.domain.entities.PokemonTypeListItem

interface PokemonTypeClickListener {
    fun onPokemonTypeClicked(pokemonTypeListItem: PokemonTypeListItem)
}