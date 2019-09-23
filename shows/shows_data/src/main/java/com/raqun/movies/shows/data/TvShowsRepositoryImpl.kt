package com.raqun.movies.shows.data

import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShowDetail
import com.raqun.movies.shows.domain.TvShowsRepository
import io.reactivex.Single
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val showsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>,
    private val showDetailsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, TvShowDetail>
) : TvShowsRepository {

    override fun getPopularTShows(page: Int): Single<PagedTvShows> = showsRemoteDataSource.getResult(page)

    override fun getShowDetail(id: Int): Single<TvShowDetail> = showDetailsRemoteDataSource.getResult(id)

}