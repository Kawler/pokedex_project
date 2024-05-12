package com.artemla.pokedex.ui.fragments.region

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemla.pokedex.databinding.FragmentRegionBinding
import com.artemla.pokedex.domain.adapters.RegionAdapter

class RegionFragment : Fragment() {

    private var _binding: FragmentRegionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[RegionViewModel::class.java]
        _binding = FragmentRegionBinding.inflate(inflater, container, false)
        setupRV()
        return binding.root
    }

    private fun setupRV() {
        binding.regionRv.layoutManager = LinearLayoutManager(context)
        binding.regionRv.adapter = RegionAdapter(viewModel.getRegionsList(), requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = RegionFragment()
        const val TAG = "RegionFragment"
    }

}