package com.raqun.movies.shows.details.presentation

import com.google.gson.annotations.SerializedName
import com.raqun.movies.core.presentation.entity.ViewEntity

data class TvShowDetailsViewEntity(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("vote_average") val votesAverage: Number?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_count") val VoteCount: Int?
) : ViewEntity