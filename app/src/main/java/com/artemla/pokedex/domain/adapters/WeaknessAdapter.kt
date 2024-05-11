package com.artemla.pokedex.domain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.domain.utils.PokemonTypesUtils
import com.artemla.pokedex.data.utils.PokemonWeaknessesUtils
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
            PokemonTypesUtils.handlePokemonTypesText(weakness, binding.rvWeaknessItem)
        }
    }

    private fun getUniqueWeaknesses(): Set<String> {
        val allWeaknesses = mutableSetOf<String>()
        for (type in pokemonTypes) {
            val weaknesses = PokemonWeaknessesUtils.getWeaknesses(type)
            allWeaknesses.addAll(weaknesses)
        }
        return allWeaknesses
    }


}