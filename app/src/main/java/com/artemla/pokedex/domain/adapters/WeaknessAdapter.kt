package com.artemla.pokedex.domain.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.data.singletons.PokemonTypeSingleton
import com.artemla.pokedex.data.singletons.PokemonWeaknesses
import com.artemla.pokedex.databinding.RvWeaknessItemBinding
import com.artemla.pokedex.domain.entities.PokemonType
import java.util.Locale

class WeaknessAdapter(private val pokemonTypes: List<PokemonType>) :
    RecyclerView.Adapter<WeaknessAdapter.WeaknessViewHolder>() {

    private val weaknesses: Set<String> = getUniqueWeaknesses()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaknessViewHolder {
        val binding =
            RvWeaknessItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaknessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeaknessViewHolder, position: Int) {
        val weakness = weaknesses.elementAt(position)
        holder.bind(weakness)
    }

    override fun getItemCount(): Int {
        return weaknesses.size
    }

    inner class WeaknessViewHolder(private val binding: RvWeaknessItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weakness: String) {
            binding.rvWeaknessItem.text = weakness.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            handlePokemonTypes(weakness,binding.rvWeaknessItem)
        }
    }

    private fun getUniqueWeaknesses(): Set<String> {
        val allWeaknesses = mutableSetOf<String>()
        for (type in pokemonTypes) {
            val weaknesses = PokemonWeaknesses.getWeaknesses(type)
            allWeaknesses.addAll(weaknesses)
        }
        return allWeaknesses
    }

    private fun handlePokemonTypes(type: String, view: TextView) {
        val pokemonTypeListItem =
            PokemonTypeSingleton.pokemonTypeList.find { it.pokemonType.name.lowercase(Locale.ENGLISH) == type.lowercase() }
        pokemonTypeListItem?.let {
            val backgroundColor = Color.parseColor(it.color)
            val textColor = Color.parseColor(it.textColor)
            val iconDrawableRes = getIconDrawableRes(it.pokemonType)
            view.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            view.setTextColor(textColor)
            view.setCompoundDrawablesWithIntrinsicBounds(iconDrawableRes, 0, 0, 0)
        }
    }

    private fun getPokemonTypeFromString(typeString: String): PokemonType {
        return when (typeString.lowercase()) {
            "normal" -> PokemonType.NORMAL
            "fire" -> PokemonType.FIRE
            "water" -> PokemonType.WATER
            "electric" -> PokemonType.ELECTRIC
            "grass" -> PokemonType.GRASS
            "ice" -> PokemonType.ICE
            "fighting" -> PokemonType.FIGHTING
            "poison" -> PokemonType.POISON
            "ground" -> PokemonType.GROUND
            "flying" -> PokemonType.FLYING
            "psychic" -> PokemonType.PSYCHIC
            "bug" -> PokemonType.BUG
            "rock" -> PokemonType.ROCK
            "ghost" -> PokemonType.GHOST
            "dragon" -> PokemonType.DRAGON
            "dark" -> PokemonType.DARK
            "steel" -> PokemonType.STEEL
            "fairy" -> PokemonType.FAIRY
            else -> PokemonType.NEW
        }
    }

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