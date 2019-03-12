package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import io.reactivex.Single
import javax.inject.Inject

class ShowsRemoteDataSource @Inject constructor(private val showsServices: ShowsServices) :
    DataSource.RetrieveRemoteDataSource<Int, PagedTvShows> {

    @SuppressLint("CheckResult")
    override fun getResult(request: Int): Single<PagedTvShows> =
        showsServices.getPopularTvShows(request)
            .map {
                return@map PagedTvShows(it.page, it.totalResults, it.totalPages, it.results)
            }
}