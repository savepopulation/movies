package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.entity.TvShowDetailEntity
import com.raqun.movies.shows.domain.TvShowDetail
import io.reactivex.functions.Function

class TvShowDetailMapper : Function<TvShowDetailEntity, TvShowDetail> {

    override fun apply(t: TvShowDetailEntity): TvShowDetail {
        return TvShowDetail(
            t.tvShowId.toInt(),
            t.name,
            t.voteAvarage,
            t.posterPath,
            t.overview,
            t.voteCount
        )
    }

}