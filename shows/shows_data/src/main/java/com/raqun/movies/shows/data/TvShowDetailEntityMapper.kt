package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.entity.TvShowDetailEntity
import com.raqun.movies.shows.domain.TvShowDetail
import io.reactivex.functions.Function

class TvShowDetailEntityMapper : Function<TvShowDetail, TvShowDetailEntity> {

    override fun apply(t: TvShowDetail): TvShowDetailEntity {
        return TvShowDetailEntity(
            t.id!!.toLong(),
            t.name!!,
            t.posterPath!!,
            t.votesAverage!!.toDouble(),
            t.overview,
            t.voteCount!!
        )
    }

}