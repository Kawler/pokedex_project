package com.artemla.pokedex.ui.fragments.region

import androidx.lifecycle.ViewModel
import com.artemla.pokedex.domain.entities.PokemonRegion
import com.artemla.pokedex.domain.entities.Region

class RegionViewModel : ViewModel() {
    fun getRegionsList(): Array<Region> {
        return PokemonRegion.entries.map { Region(it) }.toTypedArray()
    }
}