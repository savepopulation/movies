package com.raqun.movies.core.presentation.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.raqun.movies.core.presentation.entity.ViewEntity

abstract class ViewHolder<T : ViewEntity>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var itemClickListener: ((item: DisplayItem) -> Unit)? = null
    var itemLongClickListener: ((item: DisplayItem) -> Boolean)? = null

    abstract fun bind(item: T)
}