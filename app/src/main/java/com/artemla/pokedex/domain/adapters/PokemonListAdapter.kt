package com.artemla.pokedex.domain.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.data.singletons.PokemonTypeSingleton
import com.artemla.pokedex.databinding.RvPokemonsItemBinding
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.ui.fragments.home.HomeFragmentDirections
import com.bumptech.glide.Glide
import java.util.Locale

class PokemonListAdapter(private val context: Context) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(), Filterable {

    private val pokemonList = mutableListOf<PokemonDetailsResponse>()
    private var filteredPokemonList = mutableListOf<PokemonDetailsResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            RvPokemonsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = if (filteredPokemonList.isNotEmpty()) {
            filteredPokemonList[position]
        } else {
            pokemonList[position]
        }
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return if (filteredPokemonList.isNotEmpty()) {
            filteredPokemonList.size
        } else {
            pokemonList.size
        }
    }

    fun addData(newData: List<PokemonDetailsResponse>) {
        val startPosition = pokemonList.size
        pokemonList.addAll(newData)
        notifyItemRangeInserted(startPosition, newData.size)
    }

    inner class PokemonViewHolder(private val binding: RvPokemonsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(pokemon: PokemonDetailsResponse) {
            binding.homeRvFavourite.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#e36e8d"))
            binding.homeRvName.text = pokemon.name.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            binding.homeRvIndex.text = "№" + pokemon.id.toString()

            binding.homeRvType2.visibility = View.VISIBLE

            handlePokemonType(
                pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                binding.homeRvImg
            )

            if (pokemon.types.size > 1) {
                binding.homeRvType1.text = pokemon.types[0].type.name
                handlePokemonTypes(
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.homeRvType1
                )
                binding.homeRvType2.text = pokemon.types[1].type.name
                handlePokemonTypes(
                    pokemon.types[1].type.name.lowercase(Locale.ENGLISH),
                    binding.homeRvType2
                )
            } else {
                handlePokemonTypes(
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

    private fun handlePokemonType(type: String, view: ImageView) {
        for (pokemonType in PokemonType.entries) {
            if (pokemonType.name.lowercase(Locale.ENGLISH) == type) {
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

    private fun handlePokemonTypes(type: String, view: TextView) {
        val pokemonTypeListItem =
            PokemonTypeSingleton.pokemonTypeList.find { it.pokemonType.name.lowercase(Locale.ENGLISH) == type }
        pokemonTypeListItem?.let {
            val backgroundColor = Color.parseColor(it.color)
            val textColor = Color.parseColor(it.textColor)
            val iconDrawableRes = getIconDrawableRes(it.pokemonType)
            view.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            view.setTextColor(textColor)
            view.setCompoundDrawablesWithIntrinsicBounds(iconDrawableRes, 0, 0, 0)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.ENGLISH)?.trim()
                val filteredList = if (query.isNullOrEmpty()) {
                    pokemonList
                } else {
                    pokemonList.filter {
                        it.name.lowercase(Locale.ENGLISH).contains(query) || it.id.toString()
                            .contains(query)
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredPokemonList = results?.values as MutableList<PokemonDetailsResponse>
                notifyDataSetChanged()
            }
        }
    }

    fun sortByName() {
        if (filteredPokemonList.isNotEmpty()) {
            filteredPokemonList.sortBy { it.name.lowercase(Locale.ENGLISH) }
            notifyDataSetChanged()
        } else {
            pokemonList.sortBy { it.name.lowercase(Locale.ENGLISH) }
            notifyDataSetChanged()
        }
    }

    fun sortByID() {
        filteredPokemonList.sortBy { it.id }
        notifyDataSetChanged()
    }

    fun allTypes() {
        filteredPokemonList = mutableListOf()
        notifyDataSetChanged()
    }

    fun sortByDescendingID() {
        if (filteredPokemonList.isNotEmpty()) {
            filteredPokemonList.sortByDescending { it.id }
            notifyDataSetChanged()
        } else {
            pokemonList.sortByDescending { it.id }
            notifyDataSetChanged()
        }
    }

    fun filterByType(pokemonType: PokemonType) {
        filteredPokemonList = pokemonList.filter { pokemon ->
            pokemon.types.any { type ->
                type.type.name.equals(pokemonType.name, ignoreCase = true)
            }
        }.toMutableList()
        notifyDataSetChanged()
    }

    fun sortByAlphabeticalDescending() {
        if (filteredPokemonList.isNotEmpty()) {
            filteredPokemonList.sortByDescending { it.name.lowercase(Locale.ENGLISH) }
            notifyDataSetChanged()
        } else {
            pokemonList.sortByDescending { it.name.lowercase(Locale.ENGLISH) }
            notifyDataSetChanged()
        }
    }
}

