package com.raqun.movie.shows.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raqun.movies.core.presentation.recyclerview.DisplayItem
import com.raqun.movies.core.presentation.recyclerview.ViewHolder
import com.raqun.movies.core.presentation.recyclerview.ViewHolderBinder
import com.raqun.movies.core.presentation.recyclerview.ViewHolderFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PopularTvShowsViewHolder private constructor(itemView: View) : ViewHolder<PopularTvShowViewEntity>(itemView) {

    private val textViewName: TextView = itemView.findViewById(R.id.textview_name)
    private val textViewRating: TextView = itemView.findViewById(R.id.textview_rating)
    private val imageviewPoster: ImageView = itemView.findViewById(R.id.imageview_poster)

    override fun bind(item: PopularTvShowViewEntity) {
        textViewName.text = item.name
        textViewRating.text = itemView.context.getString(
            R.string.avarage_rating, item.rating.toString()
        )
        Picasso.get().load(item.picture).into(imageviewPoster)
    }

    internal class PopularTvShowsViewHolderFactory @Inject constructor() : ViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            PopularTvShowsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_popular_tv_show,
                    parent,
                    false
                )
            )
    }

    internal class PopularTvShowsViewHolderBinder @Inject constructor() : ViewHolderBinder {

        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as PopularTvShowsViewHolder).bind(item as PopularTvShowViewEntity)
        }
    }
}