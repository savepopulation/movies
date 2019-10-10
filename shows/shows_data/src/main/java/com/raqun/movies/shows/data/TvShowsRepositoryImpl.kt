package com.raqun.movies.shows.data

import android.annotation.SuppressLint
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShow
import com.raqun.movies.shows.domain.TvShowDetail
import com.raqun.movies.shows.domain.TvShowsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val showsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>,
    private val showDetailsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, TvShowDetail>,
    private val showsLocalDataSource: DataSource.Local<String, TvShow>
) : TvShowsRepository {

    @SuppressLint("CheckResult")
    override fun getPopularTShows(page: Int): Single<List<TvShow>> {
        val localShows: Observable<List<TvShow>> =
            Observable.create<List<TvShow>> { showsLocalDataSource.get(page) }
        val remoteShows: Observable<List<TvShow>> =
            showsRemoteDataSource.getResult(page).toObservable()
                .map { it.results }
                .doOnNext {
                    showsLocalDataSource.putAll(it)
                }
        return Observable.concat(remoteShows, localShows)
            .firstOrError()
    }

    override fun getShowDetail(id: Int): Single<TvShowDetail> = Single.create {
        showDetailsRemoteDataSource.getResult(id)
    }

}