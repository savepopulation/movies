package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.MoviesDb
import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.Flowable
import io.reactivex.functions.Function
import javax.inject.Inject

class TvShowsLocalDataSource @Inject constructor(
    private val db: MoviesDb,
    private val tvShowMapper: Function<TvShowEntity, TvShow>,
    private val tvShowEntityMapper: Function<TvShow, TvShowEntity>

) : DataSource.Local<String, List<TvShow>> {

    override fun get(key: String): List<TvShow>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(page: Int): Flowable<List<TvShow>> {
        val shows = if (page == -1) {
            db.tvShowsDao().getAllTvShows()
        } else {
            db.tvShowsDao().getTvShows(page)
        }
        // TODO Handle flowable return
    }

    override fun getAll(): Flowable<TvShow> {
        return get(-1)
    }

    override fun put(key: String?, data: List<TvShow>): Boolean {
        return try {
            data.forEach {
                db.tvShowsDao().addTvShow(tvShowEntityMapper.apply(it))
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun remove(value: TvShow): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeByKey(key: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
