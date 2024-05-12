package com.artemla.pokedex.domain.entities

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EqualSpacingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(spacing, spacing, spacing, spacing)
    }
}