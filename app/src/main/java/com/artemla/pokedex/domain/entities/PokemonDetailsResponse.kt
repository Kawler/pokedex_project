package com.artemla.pokedex.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PokemonDetailsResponse(
    val height: Int,
    val name: String,
    val types: @RawValue List<TypeResponse>,
    val weight: Int,
    val species: @RawValue Species,
    val sprites: @RawValue Sprites,
    var id: Int
) : Parcelable