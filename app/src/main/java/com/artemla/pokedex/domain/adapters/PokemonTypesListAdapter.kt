package com.artemla.pokedex.domain.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.databinding.RvPokemonTypesItemBinding
import com.artemla.pokedex.domain.PokemonTypeClickListener
import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.domain.entities.PokemonTypeListItem
import java.util.Locale

class PokemonTypeAdapter(private val context: Context, private val pokemonTypeList: List<PokemonTypeListItem>, private val clickListener: PokemonTypeClickListener) :
    RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvPokemonTypesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemonTypeList[position])
    }

    override fun getItemCount(): Int {
        return pokemonTypeList.size
    }

    inner class ViewHolder(private val binding: RvPokemonTypesItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val pokeType = pokemonTypeList[position]
                clickListener.onPokemonTypeClicked(pokeType)
            }
        }

        fun bind(pokemonType: PokemonTypeListItem) {
            if (pokemonType.pokemonType == PokemonType.ALL){
                binding.rvPokemonTypesItemButton.text = context.getString(R.string.all_types)
            } else {
            binding.rvPokemonTypesItemButton.text = pokemonType.pokemonType.name.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            }
            binding.rvPokemonTypesItemButton.backgroundTintList =  ColorStateList.valueOf(Color.parseColor(pokemonType.color))
            binding.rvPokemonTypesItemButton.setTextColor(Color.parseColor(pokemonType.textColor))
        }
    }
}