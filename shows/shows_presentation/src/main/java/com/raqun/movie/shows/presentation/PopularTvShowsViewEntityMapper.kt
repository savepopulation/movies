package com.raqun.movie.shows.presentation

import com.raqun.movies.core.presentation.recyclerview.DisplayItem
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.functions.Function

class PopularTvShowsViewEntityMapper : Function<TvShow, DisplayItem> {

    override fun apply(t: TvShow): DisplayItem {
        return PopularTvShowViewEntity(t.id, t.name, t.votesAverage, t.posterPath)
    }
}