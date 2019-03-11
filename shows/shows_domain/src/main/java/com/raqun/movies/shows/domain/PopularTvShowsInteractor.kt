package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import com.raqun.movies.core.model.DataHolder
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class PopularTvShowsInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.ReactiveRetrieveInteractor<PopularTvShowsInteractor.PopularTvShowsParams, PagedTvShow> {

    override fun execute(params: PopularTvShowsParams): Single<PagedTvShow?>? {
        return tvShowsRepository.getPopularTShows(params.page)
    }


    class PopularTvShowsParams(val page: Int) : Interactor.Params()
}