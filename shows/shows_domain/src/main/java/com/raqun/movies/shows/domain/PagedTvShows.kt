package com.raqun.movies.shows.domain

import com.google.gson.annotations.SerializedName

data class PagedTvShows(
    @SerializedName("page") val page: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("results") val results: List<Show>?
)