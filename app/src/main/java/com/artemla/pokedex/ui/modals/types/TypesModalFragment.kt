package com.artemla.pokedex.ui.modals.types

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemla.pokedex.R
import com.artemla.pokedex.data.singletons.PokemonTypeSingleton
import com.artemla.pokedex.databinding.FragmentFavouriteBinding
import com.artemla.pokedex.databinding.FragmentTypesModalBinding
import com.artemla.pokedex.domain.PokemonTypeClickListener
import com.artemla.pokedex.domain.adapters.PokemonTypeAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TypesModalFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: TypesModalViewModel
    private var _binding: FragmentTypesModalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypesModalBinding.inflate(inflater, container, false)

        val screenHeight = resources.displayMetrics.heightPixels
        val dialogHeight = (screenHeight * 0.7).toInt()
        binding.root.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dialogHeight)
        binding.modalTypesRv.layoutManager = LinearLayoutManager(context)
        binding.modalTypesRv.adapter = PokemonTypeAdapter(requireContext(),PokemonTypeSingleton.pokemonTypeList, parentFragment as PokemonTypeClickListener)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = TypesModalFragment()
        const val TAG = "TypesModal"
    }

}