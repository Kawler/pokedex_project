package com.artemla.pokedex.ui.fragments.modals.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemla.pokedex.databinding.FragmentOrderModalBinding
import com.artemla.pokedex.ui.listners.PokemonListOrderListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderModalFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: OrderModalViewModel
    private var _binding: FragmentOrderModalBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemonListOrderListener: PokemonListOrderListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderModalBinding.inflate(inflater, container, false)
        setButtons()

        return binding.root
    }

    private fun setButtons() {
        pokemonListOrderListener = parentFragment as PokemonListOrderListener

        binding.orderModalSmallestIndex.setOnClickListener {
            pokemonListOrderListener.onSmallestOrderClicked()
        }
        binding.orderModalHighestIndex.setOnClickListener {
            pokemonListOrderListener.onHighestOrderClicked()
        }
        binding.orderModalAz.setOnClickListener {
            pokemonListOrderListener.onAZClicked()
        }
        binding.orderModalZa.setOnClickListener {
            pokemonListOrderListener.onZAClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = OrderModalFragment()
        const val TAG = "OrderModal"
    }

}