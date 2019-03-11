package com.raqun.movies.shows.domain

import com.raqun.movies.core.model.DataHolder
import io.reactivex.Observable
import io.reactivex.Single

interface TvShowsRepository {
    fun getPopularTShows(page: Int): Single<PagedTvShow?>?
}