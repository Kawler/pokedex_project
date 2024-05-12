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
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.databinding.FragmentPokemonBinding
import com.artemla.pokedex.domain.adapters.EvolutionAdapter
import com.artemla.pokedex.domain.adapters.WeaknessAdapter
import com.artemla.pokedex.domain.entities.PokemonDetailsResponse
import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.domain.entities.TypeResponse
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
        setupViews(binding, pokemonDetailsResponse)
        setupButtonListeners(binding)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews(
        binding: FragmentPokemonBinding,
        pokemonDetailsResponse: PokemonDetailsResponse
    ) {
        handlePokemonType(requireContext(), pokemonDetailsResponse.types, binding.pokemonBg)
        setupPokemonImage(binding.pokemonImg, pokemonDetailsResponse.sprites.front_default)
        binding.apply {
            pokemonHeight.text = pokemonDetailsResponse.height.toString()
            pokemonName.text = formatPokemonName(pokemonDetailsResponse.name)
            pokemonIndex.text = "№" + pokemonDetailsResponse.id.toString()
            pokemonWeight.text = pokemonDetailsResponse.weight.toString()
            setupPokemonTypes(binding, pokemonDetailsResponse.types)
            pokemonDescription.text = viewModel.getDescription(pokemonDetailsResponse.species.url)
            setupWeaknessesRecyclerView(pokemonDetailsResponse.types, binding.pokemonWeaknessesRv)
            setupEvolutionsRecyclerView(
                binding.pokemonEvolutionsRv,
                pokemonDetailsResponse.species.url
            )
        }
    }

    private fun handlePokemonType(
        context: Context,
        types: List<TypeResponse>,
        imageView: ImageView
    ) {
        val typeName = types.firstOrNull()?.type?.name ?: return
        handlePokemonType(context, typeName, imageView)
    }

    private fun setupPokemonImage(imageView: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(imageView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_question)
                .into(imageView)
        } else {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_question
                )
            )
        }
    }

    private fun formatPokemonName(name: String): String {
        return name.lowercase(Locale.ENGLISH)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
    }

    private fun setupPokemonTypes(binding: FragmentPokemonBinding, types: List<TypeResponse>) {
        if (types.size > 1) {
            binding.apply {
                pokemonType1.text = types[0].type.name
                PokemonTypesUtils.handlePokemonTypesText(
                    types[0].type.name.lowercase(Locale.ENGLISH),
                    pokemonType1
                )
                pokemonType2.text = types[1].type.name
                PokemonTypesUtils.handlePokemonTypesText(
                    types[1].type.name.lowercase(Locale.ENGLISH),
                    pokemonType2
                )
            }
        } else {
            val typeName = types.firstOrNull()?.type?.name ?: return
            PokemonTypesUtils.handlePokemonTypesText(
                typeName.lowercase(Locale.ENGLISH),
                binding.pokemonType1
            )
            binding.pokemonType1.text = typeName
            binding.pokemonType2.visibility = View.GONE
        }
    }

    private fun setupWeaknessesRecyclerView(types: List<TypeResponse>, recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = WeaknessAdapter(types.map { getPokemonTypeFromString(it.type.name) })
    }

    private fun setupEvolutionsRecyclerView(recyclerView: RecyclerView, speciesUrl: String) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter =
            EvolutionAdapter(requireContext(), viewModel.getEvolutions(speciesUrl))
    }

    private fun setupButtonListeners(binding: FragmentPokemonBinding) {
        binding.apply {
            pokemonBackBtn.backgroundTintList =
                ColorStateList.valueOf(requireContext().getColor(R.color.white))
            pokemonFavouriteBtn.backgroundTintList =
                ColorStateList.valueOf(requireContext().getColor(R.color.favourite_inactive))
            pokemonBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
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