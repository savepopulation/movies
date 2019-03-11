package com.raqun.movies.shows.domain

import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("vote_average") val votesAverage: Int?,
    @SerializedName("poster_path") val posterPath: String?
)