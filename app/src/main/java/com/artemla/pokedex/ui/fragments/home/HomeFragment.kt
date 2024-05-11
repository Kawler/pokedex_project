package com.artemla.pokedex.ui.fragments.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemla.pokedex.ui.activities.MainActivityViewModel
import com.artemla.pokedex.R
import com.artemla.pokedex.databinding.FragmentHomeBinding
import com.artemla.pokedex.ui.listners.PokemonListOrderListener
import com.artemla.pokedex.ui.listners.PokemonTypeClickListener
import com.artemla.pokedex.domain.adapters.PokemonListAdapter
import com.artemla.pokedex.domain.entities.PokemonType
import com.artemla.pokedex.domain.entities.PokemonTypeListItem
import com.artemla.pokedex.ui.fragments.modals.order.OrderModalFragment
import com.artemla.pokedex.ui.fragments.modals.types.TypesModalFragment
import java.util.Locale
import kotlin.math.abs

class HomeFragment : Fragment(), PokemonTypeClickListener, PokemonListOrderListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var typesBottomSheet: TypesModalFragment
    private lateinit var orderBottomSheet: OrderModalFragment
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private lateinit var activityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized) {
            binding
        } else {
            viewModel =
                ViewModelProvider(this)[HomeViewModel::class.java]
            binding = FragmentHomeBinding.inflate(inflater, container, false)

            binding.homeFilterBtnType.setOnClickListener {
                typesBottomSheet = TypesModalFragment()
                typesBottomSheet.show(childFragmentManager, TypesModalFragment.TAG)
            }

            binding.homeFilterBtnOrder.setOnClickListener {
                orderBottomSheet = OrderModalFragment()
                orderBottomSheet.show(childFragmentManager, OrderModalFragment.TAG)
            }

            activityViewModel = requireActivity().run {
                ViewModelProvider(this)[MainActivityViewModel::class.java]
            }

            binding.homeRv.layoutManager = LinearLayoutManager(requireContext())
            pokemonListAdapter = PokemonListAdapter(requireContext())
            binding.homeRv.adapter = pokemonListAdapter

            binding.homeAppbarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (verticalOffset == 0) {
                    binding.homeFab.visibility = View.GONE
                } else if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                    binding.homeFab.visibility = View.VISIBLE
                } else {
                    binding.homeFab.visibility = View.GONE
                }
            }

        }

        binding.homeFab.setOnClickListener {
            binding.homeRv.scrollToPosition(0)
        }

        binding.homeSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                pokemonListAdapter.filter.filter(newText.orEmpty())
                return true
            }
        })

        activityViewModel.pokemonData.observe(viewLifecycleOwner) { pokemonData ->
            pokemonListAdapter.addData(pokemonData)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }

    override fun onPokemonTypeClicked(pokemonTypeListItem: PokemonTypeListItem) {
        binding.homeFilterBtnType.setTextColor(Color.parseColor(pokemonTypeListItem.textColor))
        binding.homeFilterBtnType.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(pokemonTypeListItem.color))
        TextViewCompat.setCompoundDrawableTintList(
            binding.homeFilterBtnType,
            ColorStateList.valueOf(Color.parseColor(pokemonTypeListItem.textColor))
        )
        if (pokemonTypeListItem.pokemonType == PokemonType.ALL) {
            binding.homeFilterBtnType.text = requireContext().getString(R.string.all_types)
            binding.homeFilterBtnOrder.text = requireContext().getString(R.string.smallest_index)
            pokemonListAdapter.allTypes()
        } else {
            binding.homeFilterBtnType.text =
                pokemonTypeListItem.pokemonType.name.lowercase(Locale.ENGLISH)
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            pokemonListAdapter.filterByType(pokemonTypeListItem.pokemonType)
        }
        typesBottomSheet.dismiss()
    }

    override fun onSmallestOrderClicked() {
        binding.homeFilterBtnOrder.text =
            requireContext().getString(R.string.smallest_index).lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
        orderBottomSheet.dismiss()
        pokemonListAdapter.sortByID()
    }

    override fun onHighestOrderClicked() {
        binding.homeFilterBtnOrder.text =
            requireContext().getString(R.string.highest_index).lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
        orderBottomSheet.dismiss()
        pokemonListAdapter.sortByDescendingID()
    }

    override fun onAZClicked() {
        binding.homeFilterBtnOrder.text =
            requireContext().getString(R.string.a_z).uppercase(Locale.ENGLISH)
        orderBottomSheet.dismiss()
        pokemonListAdapter.sortByName()
    }

    override fun onZAClicked() {
        binding.homeFilterBtnOrder.text =
            requireContext().getString(R.string.z_a).uppercase(Locale.ENGLISH)
        orderBottomSheet.dismiss()
        pokemonListAdapter.sortByAlphabeticalDescending()
    }
}