package com.artemla.pokedex.data.singletons

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.artemla.pokedex.R
import com.artemla.pokedex.domain.entities.PokemonType
import com.google.android.material.card.MaterialCardView
import java.util.Locale

object PokemonTypesUtils {
    @JvmStatic
    fun handlePokemonTypesImage(context: Context,type: String, view: ImageView) {
        val pokemonTypeListItem =
            PokemonTypeSingleton.pokemonTypeList.find { it.pokemonType.name.lowercase(Locale.ENGLISH) == type }
        pokemonTypeListItem?.let {
            val backgroundColor = Color.parseColor(it.color)
            val iconDrawableRes = getIconDrawableRes(it.pokemonType)
            view.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            view.setImageDrawable(AppCompatResources.getDrawable(context,iconDrawableRes))
        }
    }

    @JvmStatic
    fun handlePokemonType(context: Context,type: String, view: View) {
        for (pokemonType in PokemonType.entries) {
            if (pokemonType.name.lowercase(Locale.ENGLISH) == type.lowercase(Locale.ENGLISH)) {
                when (pokemonType) {
                    PokemonType.NORMAL -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_normal_type)
                    }

                    PokemonType.FIRE -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_fire_type)
                    }

                    PokemonType.WATER -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_water_type)
                    }

                    PokemonType.ELECTRIC -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_electric_type)
                    }

                    PokemonType.GRASS -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_grass_type)
                    }

                    PokemonType.ICE -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_ice_type)
                    }

                    PokemonType.FIGHTING -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_fighting_type)
                    }

                    PokemonType.POISON -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_poison_type)
                    }

                    PokemonType.GROUND -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_ground_type)
                    }

                    PokemonType.FLYING -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_flying_type)
                    }

                    PokemonType.PSYCHIC -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_psychic_type)
                    }

                    PokemonType.BUG -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_bug_type)
                    }

                    PokemonType.ROCK -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_rock_type)
                    }

                    PokemonType.GHOST -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_ghost_type)
                    }

                    PokemonType.DRAGON -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_dragon_type)
                    }

                    PokemonType.DARK -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_dark_type)
                    }

                    PokemonType.STEEL -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_iron_type)
                    }

                    PokemonType.FAIRY -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_fairy_type)
                    }

                    PokemonType.ALL -> {
                    }

                    PokemonType.NEW -> {
                    }
                }
                break
            }
        }
    }

    @JvmStatic
    fun handlePokemonTypesText(type: String, view: TextView) {
        val pokemonTypeListItem =
            PokemonTypeSingleton.pokemonTypeList.find { it.pokemonType.name.lowercase(Locale.ENGLISH) == type.lowercase(
                Locale.ENGLISH) }
        pokemonTypeListItem?.let {
            val backgroundColor = Color.parseColor(it.color)
            val textColor = Color.parseColor(it.textColor)
            val iconDrawableRes = getIconDrawableRes(it.pokemonType)
            view.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            view.setTextColor(textColor)
            view.setCompoundDrawablesWithIntrinsicBounds(iconDrawableRes, 0, 0, 0)
        }
    }

    @JvmStatic
    private fun getIconDrawableRes(pokemonType: PokemonType): Int {
        return when (pokemonType) {
            PokemonType.NORMAL -> R.drawable.ic_normal_type
            PokemonType.FIRE -> R.drawable.ic_fire_type
            PokemonType.WATER -> R.drawable.ic_water_type
            PokemonType.ELECTRIC -> R.drawable.ic_electric_type
            PokemonType.GRASS -> R.drawable.ic_grass_type
            PokemonType.ICE -> R.drawable.ic_ice_type
            PokemonType.FIGHTING -> R.drawable.ic_fighting_type
            PokemonType.POISON -> R.drawable.ic_poison_type
            PokemonType.GROUND -> R.drawable.ic_ground_type
            PokemonType.FLYING -> R.drawable.ic_flying_type
            PokemonType.PSYCHIC -> R.drawable.ic_psychic_type
            PokemonType.BUG -> R.drawable.ic_bug_type
            PokemonType.ROCK -> R.drawable.ic_rock_type
            PokemonType.GHOST -> R.drawable.ic_ghost_type
            PokemonType.DRAGON -> R.drawable.ic_dragon_type
            PokemonType.DARK -> R.drawable.ic_dark_type
            PokemonType.STEEL -> R.drawable.ic_steel_type
            PokemonType.FAIRY -> R.drawable.ic_fairy_type
            else -> R.drawable.ic_normal_type // Default icon
        }
    }
}