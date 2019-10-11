package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.MoviesDb
import com.raqun.movies.core.data.db.entity.TvShowDetailEntity
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.TvShowDetail
import io.reactivex.Flowable
import io.reactivex.functions.Function
import javax.inject.Inject

class TvShowDetailsLocalDataSource @Inject constructor(
    private val db: MoviesDb,
    private val tvShowDetailMapper: Function<TvShowDetailEntity, TvShowDetail>,
    private val tvShowDetailEntityMapper: Function<TvShowDetail, TvShowDetailEntity>
) : DataSource.FlowableLocal<Int, TvShowDetail> {

    override fun get(key: Int?): Flowable<TvShowDetail> {
        return if (key == null) {
            Flowable.empty()
        } else {
            db.tvShowDetailsDao().getTvShowDetail(key.toLong()).map { t: TvShowDetailEntity ->
                tvShowDetailMapper.apply(t)
            }
        }
    }

    override fun put(key: Int?, data: TvShowDetail): Boolean {
        return try {
            db.tvShowDetailsDao().addTvShowDetail(tvShowDetailEntityMapper.apply(data))
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun remove(value: TvShowDetail): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}