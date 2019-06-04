package com.raqun.movie.shows.presentation

import com.raqun.movies.core.presentation.entity.ViewEntity
import com.raqun.movies.core.presentation.recyclerview.DisplayItem
import java.lang.NumberFormatException

class PopularTvShowViewEntity(
    val id: Int?,
    val name: String?,
    val rating: Number?,
    val picture: String?,
    val overview: String?,
    val voteCount: Int?
) : ViewEntity, DisplayItem {

    override fun type() = PopularTvShowsPresentationConstants.TYPES.SHOW
}