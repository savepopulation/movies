package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShow
import com.raqun.movies.shows.domain.TvShowDetail
import com.raqun.movies.shows.domain.TvShowsRepository
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val showsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>,
    private val showDetailsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, TvShowDetail>,
    private val showsLocalDataSource: DataSource.FlowableLocal<Int, List<TvShow>>,
    private val showDetailsLocalDataSource: DataSource.FlowableLocal<Int, TvShowDetail>
) : TvShowsRepository {

    @SuppressLint("CheckResult")
    override fun getPopularTShows(page: Int): Flowable<List<TvShow>> {
        showsRemoteDataSource.getResult(page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                showsLocalDataSource.put(null, it.results)
            }, onError = {
                // ignored
            })
        return showsLocalDataSource.get(null)
    }

    override fun getShowDetail(id: Int): Flowable<TvShowDetail> =
        showDetailsRemoteDataSource.getResult(id).toFlowable()

}