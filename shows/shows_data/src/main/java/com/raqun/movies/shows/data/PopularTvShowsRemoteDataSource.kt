package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import io.reactivex.Single
import javax.inject.Inject

class PopularTvShowsRemoteDataSource @Inject constructor(private val tvShowsServices: TvShowsServices) :
    DataSource.RetrieveRemoteDataSource<Int, PagedTvShows> {

    @SuppressLint("CheckResult")
    override fun getResult(request: Int): Single<PagedTvShows> =
        tvShowsServices.getPopularTvShows(request)
            .map {
                if (it.page == null
                    || it.totalResults == null
                    || it.totalPages == null
                    || it.results == null
                ) {
                    throw Exception("Unexpected response!")
                }
                return@map PagedTvShows(it.page!!, it.totalResults!!, it.totalPages!!, it.results!!)
            }
}