package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularTvShowsInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.ReactiveRetrieveInteractor<GetPopularTvShowsInteractor.Params, List<TvShow>> {

    override fun execute(params: Params): Observable<List<TvShow>> {
        require(params.page > 0) { "Invalid current page number" }
        return tvShowsRepository.getPopularTShows(params.page).toObservable()
    }

    class Params(val page: Int, val totalPage: Int) : Interactor.Params()
}