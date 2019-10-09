package com.raqun.movies.shows.domain

import io.reactivex.Flowable

interface TvShowsRepository {
    fun getPopularTShows(page: Int): Flowable<List<TvShow>>

    fun getShowDetail(id: Int): Flowable<TvShowDetail>
}