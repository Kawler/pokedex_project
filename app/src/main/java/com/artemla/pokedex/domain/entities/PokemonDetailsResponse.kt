package com.artemla.pokedex.domain.entities
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PokemonDetailsResponse(
    val height: Double,
    val name: String,
    val types: @RawValue List<TypeResponse>,
    val weight: Double,
    val species: @RawValue Species,
    val sprites: @RawValue Sprites,
    var id: Int
) : Parcelable