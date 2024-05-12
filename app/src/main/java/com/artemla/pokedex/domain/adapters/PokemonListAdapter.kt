package com.artemla.pokedex.domain.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.domain.utils.PokemonTypesUtils
import com.artemla.pokedex.databinding.RvPokemonsItemBinding
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.ui.fragments.home.HomeFragmentDirections
import com.bumptech.glide.Glide
import java.util.Locale

class PokemonListAdapter(private val context: Context) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(), Filterable {

    private val pokemonSet = mutableSetOf<PokemonDetailsResponse>()
    private var filteredPokemonSet = mutableSetOf<PokemonDetailsResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            RvPokemonsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = if (filteredPokemonSet.isNotEmpty()) {
            filteredPokemonSet.elementAtOrNull(position)
        } else {
            pokemonSet.elementAtOrNull(position)
        }
        pokemon?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return if (filteredPokemonSet.isNotEmpty()) {
            filteredPokemonSet.size
        } else {
            pokemonSet.size
        }
    }

    fun addData(newData: List<PokemonDetailsResponse>) {
        val startPosition = pokemonSet.size
        val diff = newData.size - pokemonSet.size
        if (newData.size > pokemonSet.size) {
            pokemonSet.addAll(newData.subList(pokemonSet.size, newData.size))
            notifyItemRangeInserted(startPosition, diff)
        } else {
            pokemonSet.addAll(newData)
            notifyItemRangeInserted(startPosition, newData.size)
        }
    }

    inner class PokemonViewHolder(private val binding: RvPokemonsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(pokemon: PokemonDetailsResponse) {
            binding.homeRvFavourite.backgroundTintList =
                ColorStateList.valueOf(context.getColor(R.color.favourite_inactive))
            binding.homeRvName.text = pokemon.name.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            binding.homeRvIndex.text = "№" + pokemon.id.toString()

            binding.homeRvType2.visibility = View.VISIBLE

            PokemonTypesUtils.handlePokemonType(
                context,
                pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                binding.homeRvImg
            )

            if (pokemon.types.size > 1) {
                binding.homeRvType1.text = pokemon.types[0].type.name
                PokemonTypesUtils.handlePokemonTypesText(
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.homeRvType1
                )
                binding.homeRvType2.text = pokemon.types[1].type.name
                PokemonTypesUtils.handlePokemonTypesText(
                    pokemon.types[1].type.name.lowercase(Locale.ENGLISH),
                    binding.homeRvType2
                )
            } else {
                PokemonTypesUtils.handlePokemonTypesText(
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.homeRvType1
                )
                binding.homeRvType1.text = pokemon.types[0].type.name
                binding.homeRvType2.visibility = View.GONE
            }
            Glide
                .with(itemView)
                .load(pokemon.sprites.front_default)
                .placeholder(R.drawable.ic_question)
                .into(binding.homeRvImg)
            binding.homeRvPokemon.setOnClickListener {
                val action = HomeFragmentDirections.actionNavigationHomeToPokemonFragment(pokemon)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.ENGLISH)?.trim()
                val filteredList = if (query.isNullOrEmpty()) {
                    pokemonSet
                } else {
                    pokemonSet.filter {
                        it.name.lowercase(Locale.ENGLISH).contains(query) || it.id.toString()
                            .contains(query)
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val values = results?.values
                if (values is MutableList<*>) {
                    @Suppress("UNCHECKED_CAST")
                    val data = values as MutableList<PokemonDetailsResponse>
                    filteredPokemonSet = data.toMutableSet()
                } else if (values is MutableSet<*>) {
                    @Suppress("UNCHECKED_CAST")
                    filteredPokemonSet = values as MutableSet<PokemonDetailsResponse>
                }
                notifyDataSetChanged()
            }
        }
    }

    fun sortByName() {
        val sortedList = if (filteredPokemonSet.isNotEmpty()) {
            filteredPokemonSet.sortedBy { it.name.lowercase(Locale.ENGLISH) }
        } else {
            pokemonSet.sortedBy { it.name.lowercase(Locale.ENGLISH) }
        }
        filteredPokemonSet.clear()
        filteredPokemonSet.addAll(sortedList)
        notifyDataSetChanged()
    }

    fun sortByID() {
        val sortedList = pokemonSet.sortedBy { it.id }
        pokemonSet.clear()
        pokemonSet.addAll(sortedList)
        val sortedFilterList = filteredPokemonSet.sortedBy { it.id }
        filteredPokemonSet.clear()
        filteredPokemonSet.addAll(sortedFilterList)
        notifyDataSetChanged()
    }

    fun allTypes() {
        filteredPokemonSet.clear()
        notifyDataSetChanged()
    }

    fun sortByDescendingID() {
        val sortedList = if (filteredPokemonSet.isNotEmpty()) {
            filteredPokemonSet.sortedByDescending { it.id }
        } else {
            pokemonSet.sortedByDescending { it.id }
        }
        filteredPokemonSet.clear()
        filteredPokemonSet.addAll(sortedList)
        notifyDataSetChanged()
    }

    fun filterByType(pokemonType: PokemonType) {
        filteredPokemonSet.clear()
        filteredPokemonSet.addAll(pokemonSet.filter { pokemon ->
            pokemon.types.any { type ->
                type.type.name.equals(pokemonType.name, ignoreCase = true)
            }
        })
        notifyDataSetChanged()
    }

    fun sortByAlphabeticalDescending() {
        val sortedList = if (filteredPokemonSet.isNotEmpty()) {
            filteredPokemonSet.sortedByDescending { it.name.lowercase(Locale.ENGLISH) }
        } else {
            pokemonSet.sortedByDescending { it.name.lowercase(Locale.ENGLISH) }
        }
        filteredPokemonSet.clear()
        filteredPokemonSet.addAll(sortedList)
        notifyDataSetChanged()
    }
}

