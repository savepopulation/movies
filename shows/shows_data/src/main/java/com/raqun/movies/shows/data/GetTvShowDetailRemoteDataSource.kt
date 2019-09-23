package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShowDetail
import io.reactivex.Single
import javax.inject.Inject

class GetTvShowDetailRemoteDataSource @Inject constructor(private val tvShowsServices: TvShowsServices) :
    DataSource.RetrieveRemoteDataSource<Int, TvShowDetail> {

    @SuppressLint("CheckResult")
    override fun getResult(request: Int): Single<TvShowDetail> = tvShowsServices.getTvShowDetail(request)
}