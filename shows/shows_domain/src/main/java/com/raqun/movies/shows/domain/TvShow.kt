package com.raqun.movies.shows.domain

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("vote_average") val votesAverage: Number?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_count") val VoteCount: Int?
)