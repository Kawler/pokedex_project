package com.artemla.pokedex.domain.entities

data class TypeResponse(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)

data class Species(
    val name: String,
    val url: String
)

data class Sprites(
    val front_default: String?
)