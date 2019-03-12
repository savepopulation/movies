package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import io.reactivex.Observable
import javax.inject.Inject

class PopularTvShowsInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.ReactiveRetrieveInteractor<PopularTvShowsInteractor.PopularTvShowsParams, PagedTvShows> {

    override fun execute(params: PopularTvShowsParams): Observable<PagedTvShows> {
        return tvShowsRepository.getPopularTShows(params.page).toObservable()
    }


    class PopularTvShowsParams(val page: Int) : Interactor.Params()
}