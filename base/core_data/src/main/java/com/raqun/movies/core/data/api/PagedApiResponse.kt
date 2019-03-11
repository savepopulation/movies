package com.raqun.movies.core.data.api

import com.google.gson.annotations.SerializedName

data class PagedApiResponse<T>(
    @SerializedName("status_code") val code: Int?,
    @SerializedName("status_message") val message: String?,
    @SerializedName("page") val page: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("results") val results: T?
)
