package com.raqun.movies.shows.domain.util

import com.raqun.movies.shows.domain.TvShow

class DataProvider {
    companion object {
        fun createDummyTvShows(count: Int): List<TvShow> {
            val dummyTvSHows = ArrayList<TvShow>()
            for (i in 0..count) {
                dummyTvSHows.add(TvShow(i, "Tv Show $i", i, null))
            }

            return dummyTvSHows
        }
    }
}