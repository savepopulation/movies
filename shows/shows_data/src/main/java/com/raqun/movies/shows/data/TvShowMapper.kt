package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.functions.Function

class TvShowMapper : Function<TvShowEntity, TvShow> {

    override fun apply(t: TvShowEntity): TvShow {
        return TvShow(
            t.tvShowId.toInt(),
            t.name,
            t.voteAvarage,
            t.posterPath,
            t.popularity
        )
    }

}