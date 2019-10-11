package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.MoviesDb
import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class TvShowsLocalDataSource @Inject constructor(
    private val db: MoviesDb,
    private val tvShowMapper: Function<TvShowEntity, TvShow>,
    private val tvShowEntityMapper: Function<TvShow, TvShowEntity>
) : DataSource.FlowableLocal<Int, List<TvShow>> {

    override fun get(key: Int?): Flowable<List<TvShow>> =
        db.tvShowsDao().getTvShows().map {
            it.map { tvShowEntity ->
                tvShowMapper.apply(tvShowEntity)
            }
        }

    override fun put(key: Int?, data: List<TvShow>): Boolean {
        return try {
            val shows = Observable.fromIterable(data)
                .map { tvShow ->
                    tvShowEntityMapper.apply(tvShow)
                }
                .toList()
                .blockingGet()
            db.tvShowsDao().addTvShows(shows)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun remove(value: List<TvShow>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}