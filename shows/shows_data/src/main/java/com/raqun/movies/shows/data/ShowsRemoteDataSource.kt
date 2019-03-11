package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.api.ApiConstants
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShow
import io.reactivex.Single
import javax.inject.Inject

class ShowsRemoteDataSource @Inject constructor(private val showsServices: ShowsServices) :
    DataSource.RetrieveRemoteDataSource<Int, PagedTvShow> {

    @SuppressLint("CheckResult")
    override fun getResult(request: Int): Single<PagedTvShow?>? =
        showsServices.getPopularTvShows(request)
            ?.map {
                if (it.code != ApiConstants.SUCCESS_CODE) {
                    throw ApiException(it.code, it.message)
                }
                return@map PagedTvShow(it.page, it.totalResults, it.totalPages, it.results)
            }
}