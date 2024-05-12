package com.artemla.pokedex.ui.fragments.pokemon

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemla.pokedex.R
import com.artemla.pokedex.databinding.FragmentPokemonBinding
import com.artemla.pokedex.domain.adapters.EvolutionAdapter
import com.artemla.pokedex.domain.adapters.WeaknessAdapter
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.domain.utils.PokemonTypesUtils
import com.bumptech.glide.Glide
import java.util.Locale

class PokemonFragment() : Fragment() {

    private lateinit var viewModel: PokemonViewModel
    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        val pokemonDetailsResponse: PokemonDetailsResponse =
            requireArguments().getParcelable("pokemonDetails")!!
        handlePokemonType(
            requireContext(),
            pokemonDetailsResponse.types[0].type.name,
            binding.pokemonBg
        )
        if (pokemonDetailsResponse.sprites.front_default != null) {
            Glide
                .with(requireContext())
                .load(pokemonDetailsResponse.sprites.front_default)
                .placeholder(R.drawable.ic_question)
                .into(binding.pokemonImg)
        } else {
            binding.pokemonImg.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_question
                )
            )
        }
        binding.pokemonHeight.text = pokemonDetailsResponse.height.toString()
        binding.pokemonName.text = pokemonDetailsResponse.name.lowercase(Locale.ENGLISH)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
        binding.pokemonIndex.text = "№" + pokemonDetailsResponse.id.toString()
        binding.pokemonWeight.text = pokemonDetailsResponse.weight.toString()
        if (pokemonDetailsResponse.types.size > 1) {
            binding.pokemonType1.text = pokemonDetailsResponse.types[0].type.name
            PokemonTypesUtils.handlePokemonTypesText(
                pokemonDetailsResponse.types[0].type.name.lowercase(Locale.ENGLISH),
                binding.pokemonType1
            )
            binding.pokemonType2.text = pokemonDetailsResponse.types[1].type.name
            PokemonTypesUtils.handlePokemonTypesText(
                pokemonDetailsResponse.types[1].type.name.lowercase(Locale.ENGLISH),
                binding.pokemonType2
            )
        } else {
            PokemonTypesUtils.handlePokemonTypesText(
                pokemonDetailsResponse.types[0].type.name.lowercase(Locale.ENGLISH),
                binding.pokemonType1
            )
            binding.pokemonType1.text = pokemonDetailsResponse.types[0].type.name
            binding.pokemonType2.visibility = View.GONE
        }

        binding.pokemonDescription.text =
            viewModel.getDescription(pokemonDetailsResponse.species.url)

        binding.pokemonWeaknessesRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.pokemonWeaknessesRv.adapter =
            WeaknessAdapter(pokemonDetailsResponse.types.map { getPokemonTypeFromString(it.type.name) })
        binding.pokemonEvolutionsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.pokemonEvolutionsRv.adapter = EvolutionAdapter(
            requireContext(),
            viewModel.getEvolutions(pokemonDetailsResponse.species.url)
        )

        binding.pokemonBackBtn.backgroundTintList =
            ColorStateList.valueOf(requireContext().getColor(R.color.white))
        binding.pokemonFavouriteBtn.backgroundTintList =
            ColorStateList.valueOf(requireContext().getColor(R.color.favourite_inactive))
        binding.pokemonBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
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
            else -> PokemonType.ALL
        }
    }


    private fun handlePokemonType(context: Context, type: String, view: ImageView) {
        for (pokemonType in PokemonType.entries) {
            if (pokemonType.name.lowercase(Locale.ENGLISH) == type) {
                when (pokemonType) {
                    PokemonType.NORMAL -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_normal_header)
                    }

                    PokemonType.FIRE -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_fire_header)
                    }

                    PokemonType.WATER -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_water_header)
                    }

                    PokemonType.ELECTRIC -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_electric_header)
                    }

                    PokemonType.GRASS -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_grass_header)
                    }

                    PokemonType.ICE -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_ice_header)
                    }

                    PokemonType.FIGHTING -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_fighting_header)
                    }

                    PokemonType.POISON -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_poison_headert)
                    }

                    PokemonType.GROUND -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_ground_header)
                    }

                    PokemonType.FLYING -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_flying_header)
                    }

                    PokemonType.PSYCHIC -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_psychic_header)
                    }

                    PokemonType.BUG -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_bug_header)
                    }

                    PokemonType.ROCK -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_rock_header)
                    }

                    PokemonType.GHOST -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_ghost_header)
                    }

                    PokemonType.DRAGON -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_dragon_header)
                    }

                    PokemonType.DARK -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_dark_header)
                    }

                    PokemonType.STEEL -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_steel_header)
                    }

                    PokemonType.FAIRY -> {
                        view.background =
                            ContextCompat.getDrawable(context, R.drawable.bg_fairy_header)
                    }

                    PokemonType.ALL -> {
                    }
                }
                break
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PokemonFragment()
        const val TAG = "PokemonFragment"
    }

}