package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.db.MoviesDb
import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShow
import com.raqun.movies.shows.domain.TvShowDetail
import com.raqun.movies.shows.domain.TvShowsRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val showsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>,
    private val showDetailsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, TvShowDetail>,
    private val db: MoviesDb,
    private val tvShowMapper: Function<TvShowEntity, TvShow>,
    private val tvShowEntityMapper: Function<TvShow, TvShowEntity>
) : TvShowsRepository {

    @SuppressLint("CheckResult")
    override fun getPopularTShows(page: Int): Flowable<List<TvShow>> {
        showsRemoteDataSource.getResult(page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                val shows = Observable.fromIterable(it.results)
                    .map { tvShow ->
                        tvShowEntityMapper.apply(tvShow)
                    }
                    .toList()
                    .blockingGet()
                db.tvShowsDao().addTvShows(shows)
            }, onError = {
                // ignored
            })
        return db.tvShowsDao().getTvShows().map {
            it.map { tvShowEntity ->
                tvShowMapper.apply(tvShowEntity)
            }
        }
    }

    override fun getShowDetail(id: Int): Flowable<TvShowDetail> =
        showDetailsRemoteDataSource.getResult(id).toFlowable()

}