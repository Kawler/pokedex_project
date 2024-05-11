package com.artemla.pokedex.domain.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.data.repositories.PokemonRepositoryImpl
import com.artemla.pokedex.domain.utils.PokemonTypesUtils
import com.artemla.pokedex.databinding.RvEvolutionItemBinding
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonEvolution
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
                PokemonTypesUtils.handlePokemonTypesImage(
                    context,
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.rvEvolutionType1
                )
                PokemonTypesUtils.handlePokemonTypesImage(
                    context,
                    pokemon.types[1].type.name.lowercase(Locale.ENGLISH),
                    binding.rvEvolutionType2
                )
            } else {
                PokemonTypesUtils.handlePokemonTypesImage(
                    context,
                    pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                    binding.rvEvolutionType1
                )
                binding.rvEvolutionType2.visibility = View.GONE
            }
            PokemonTypesUtils.handlePokemonType(
                context,
                pokemon.types[0].type.name.lowercase(Locale.ENGLISH),
                binding.rvEvolutionBg
            )
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
        val repository = PokemonRepositoryImpl
        val result: MutableList<PokemonDetailsResponse>
        runBlocking {
            result = repository.fetchPokemonDetails(evolutionList.map { "pokemon/" + it.name })
        }
        return result
    }
}