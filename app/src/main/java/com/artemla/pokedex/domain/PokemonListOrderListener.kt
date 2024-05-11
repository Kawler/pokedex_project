package com.artemla.pokedex.domain

interface PokemonListOrderListener {
    fun onSmallestOrderClicked()
    fun onHighestOrderClicked()
    fun onAZClicked()
    fun onZAClicked()
}