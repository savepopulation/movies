package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.functions.Function

class TvShowEntityMapper : Function<TvShow, TvShowEntity> {

    override fun apply(p0: TvShow): TvShowEntity {
        return TvShowEntity(
            p0.id!!.toLong(),
            p0.name!!,
            p0.posterPath!!,
            p0.votesAverage!!
        )
    }

}