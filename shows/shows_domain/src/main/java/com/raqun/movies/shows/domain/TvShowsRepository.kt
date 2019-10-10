package com.raqun.movies.shows.domain

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface TvShowsRepository {
    fun getPopularTShows(page: Int): Single<List<TvShow>>

    fun getShowDetail(id: Int): Single<TvShowDetail>
}