package com.raqun.movies.shows.data

import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShow
import com.raqun.movies.shows.domain.TvShowsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val showsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShow>) :
    TvShowsRepository {

    override fun getPopularTShows(page: Int): Single<PagedTvShow?>? {
        return showsRemoteDataSource.getResult(page)
    }

}