package com.raqun.movies.shows.data

import android.util.Log
import com.raqun.movies.core.data.db.MoviesDb
import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.functions.Function
import javax.inject.Inject

class TvShowsLocalDataSource @Inject constructor(
    private val db: MoviesDb,
    private val tvShowMapper: Function<TvShowEntity, TvShow>,
    private val tvShowEntityMapper: Function<TvShow, TvShowEntity>

) : DataSource.Local<String, TvShow> {

    override fun get(key: String): TvShow? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(page: Int): List<TvShow> {
        val shows = if (page == -1) {
            db.tvShowsDao().getAllTvShows()
        } else {
            db.tvShowsDao().getTvShows(page)
        }

        return shows.map {
            Log.e("local shows", shows.size.toString())
            tvShowMapper.apply(it)
        }
    }

    override fun getAll(): List<TvShow> {
        return get(-1)
    }

    override fun putAll(data: List<TvShow>) {
        for (tvshow in data) {
            Log.e("writing", data.size.toString())
            val result = put(tvshow.id.toString(), tvshow)
            Log.e("result", result.toString())
        }
    }

    override fun put(key: String?, data: TvShow): Boolean {
        return db.tvShowsDao().addTvShow(tvShowEntityMapper.apply(data)) > 0
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
