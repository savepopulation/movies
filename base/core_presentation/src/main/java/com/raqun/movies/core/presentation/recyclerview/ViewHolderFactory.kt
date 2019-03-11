package com.raqun.movies.core.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewHolderFactory {
    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
}