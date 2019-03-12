package com.raqun.movies.shows.domain

import io.reactivex.Single

interface TvShowsRepository {
    fun getPopularTShows(page: Int): Single<PagedTvShows>
}