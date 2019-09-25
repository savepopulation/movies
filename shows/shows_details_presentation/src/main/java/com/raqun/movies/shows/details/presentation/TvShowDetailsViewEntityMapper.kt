package com.raqun.movies.shows.details.presentation

import com.raqun.movies.shows.domain.TvShowDetail
import io.reactivex.functions.Function

class TvShowDetailsViewEntityMapper : Function<TvShowDetail, TvShowDetailsViewEntity> {

    override fun apply(t: TvShowDetail): TvShowDetailsViewEntity {
        return TvShowDetailsViewEntity(
            t.id,
            t.name,
            t.votesAverage,
            t.posterPath,
            t.overview,
            t.VoteCount
        )
    }

}