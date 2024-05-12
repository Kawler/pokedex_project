package com.artemla.pokedex.domain.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.artemla.pokedex.R
import com.artemla.pokedex.data.utils.RegionDescriptions
import com.artemla.pokedex.databinding.RvRegionItemBinding
import com.artemla.pokedex.domain.entities.PokemonRegion
import com.artemla.pokedex.domain.entities.Region
import java.util.Locale

class RegionAdapter(private val regions: Array<Region>, private val context: Context) :
    RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {

    inner class RegionViewHolder(private val binding: RvRegionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(region: Region) {
            binding.regionItemName.text = region.name.name.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            binding.regionOutlinedName.text = region.name.name.lowercase(Locale.ENGLISH)
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
            handleRegionType(
                region.name,
                binding.regionItemDescription,
                binding.regionItemImg,
                context
            )
            binding.regionItemCard.setOnClickListener {
                if (binding.regionItemDescription.visibility == View.VISIBLE) {
                    binding.regionItemDescription.visibility = View.GONE
                } else binding.regionItemDescription.visibility = View.VISIBLE
            }
        }
    }

    private fun handleRegionType(
        regionType: PokemonRegion,
        tv: TextView,
        mt: ImageView,
        context: Context
    ) {
        when (regionType) {
            PokemonRegion.KANTO -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_kanto)
                tv.text = RegionDescriptions.KANTO
            }

            PokemonRegion.JOHTO -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_johto)
                tv.text = RegionDescriptions.JOHTO
            }

            PokemonRegion.HOENN -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_hoenn)
                tv.text = RegionDescriptions.HOENN
            }

            PokemonRegion.SINNOH -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_sinnoh)
                tv.text = RegionDescriptions.SINNOH
            }

            PokemonRegion.UNOVA -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_unove)
                tv.text = RegionDescriptions.UNOVA
            }

            PokemonRegion.KALOS -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_kalos)
                tv.text = RegionDescriptions.KALOS
            }

            PokemonRegion.ALOLA -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_alola)
                tv.text = RegionDescriptions.ALOLA
            }

            PokemonRegion.GALAR -> {
                mt.background = ContextCompat.getDrawable(context, R.drawable.bg_galar)
                tv.text = RegionDescriptions.GALAR
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvRegionItemBinding.inflate(inflater, parent, false)
        return RegionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        val region = regions[position]
        holder.bind(region)
    }

    override fun getItemCount(): Int {
        return regions.size
    }
}