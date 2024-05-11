package com.artemla.pokedex.domain.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.data.repositories.PokemonRepository
import com.artemla.pokedex.data.singletons.PokemonTypeSingleton
import com.artemla.pokedex.databinding.RvEvolutionItemBinding
import com.artemla.pokedex.domain.entities.EvolutionSpecies
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonEvolution
import com.artemla.pokedex.domain.entities.PokemonType
import com.bumptech.glide.Glide
import kotlinx.coroutines.runBlocking
import java.util.Locale


class EvolutionAdapter(
    private val context: Context,
    private val evolutionList: List<PokemonEvolution>
) :
    RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvEvolutionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return evolutionList.size
    }

    inner class ViewHolder(private val binding: RvEvolutionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val data = getData()
        @SuppressLint("SetTextI18n")
        fun bind() {
            val pokemon = data[adapterPosition]
            if (pokemon.types.size > 1) {
                handlePokemonTypes(
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.rvEvolutionType1
                )
                handlePokemonTypes(
                    pokemon.types[1].type.name.lowercase(Locale.ENGLISH),
                    binding.rvEvolutionType2
                )
            } else {
                handlePokemonTypes(
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.rvEvolutionType1
                )
                binding.rvEvolutionType2.visibility = View.GONE
            }
            handlePokemonType(pokemon.types[0].type.name.lowercase(Locale.ENGLISH), binding.rvEvolutionBg)
            Glide
                .with(itemView)
                .load(pokemon.sprites.front_default)
                .placeholder(R.drawable.ic_question)
                .into(binding.rvEvolutionImg)
            binding.rvEvolutionName.text = pokemon.name.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            binding.rvEvolutionId.text = "№" + pokemon.id.toString()
        }
    }

    private fun getData(): MutableList<PokemonDetailsResponse> {
        val repository = PokemonRepository
        val result: MutableList<PokemonDetailsResponse>
        runBlocking {
            result = repository.fetchPokemonDetails(evolutionList.map { "pokemon/"+it.name })
        }
        return result
    }

    private fun handlePokemonTypes(type: String, view: ImageView) {
        val pokemonTypeListItem =
            PokemonTypeSingleton.pokemonTypeList.find { it.pokemonType.name.lowercase(Locale.ENGLISH) == type }
        pokemonTypeListItem?.let {
            val backgroundColor = Color.parseColor(it.color)
            val iconDrawableRes = getIconDrawableRes(it.pokemonType)
            view.backgroundTintList = ColorStateList.valueOf(backgroundColor)
            view.setImageDrawable(AppCompatResources.getDrawable(context,iconDrawableRes))
        }
    }

    private fun handlePokemonType(type: String, view: View) {
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