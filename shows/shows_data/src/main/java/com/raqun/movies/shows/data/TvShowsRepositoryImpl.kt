package com.raqun.movies.shows.data

import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShowsRepository
import io.reactivex.Single
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(private val showsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>) :
    TvShowsRepository {

    override fun getPopularTShows(page: Int): Single<PagedTvShows> {
        return showsRemoteDataSource.getResult(page)
    }

}