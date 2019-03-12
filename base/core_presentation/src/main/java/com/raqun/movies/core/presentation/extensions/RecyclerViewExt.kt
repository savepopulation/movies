package com.raqun.movies.core.presentation.extensions

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raqun.movies.core.presentation.recyclerview.RecyclerViewAdapter

/*
 * Setups RecyclerView with Default parameters
 */
@SuppressLint("WrongConstant")
fun RecyclerView.setup(
    context: Context,
    orientation: Int = LinearLayoutManager.VERTICAL,
    adapter: RecyclerViewAdapter?
) {
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
    this.setHasFixedSize(false)
    adapter?.let {
        this.adapter = adapter
    }
}