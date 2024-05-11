package com.artemla.pokedex.ui.fragments.region

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemla.pokedex.R
import com.artemla.pokedex.databinding.FragmentRegionBinding

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
        return binding.root
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