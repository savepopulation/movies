package com.raqun.movies.shows.data

import android.database.Observable
import com.raqun.movies.core.data.api.ApiConstants
import com.raqun.movies.core.data.api.PagedApiResponse
import com.raqun.movies.shows.domain.Show
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsServices {
    @GET("/tv/popular?api_key=${ApiConstants.API_KEY}")
    fun getPopularTvShows(page: Int = 1): Single<PagedApiResponse<List<Show>>>?
}