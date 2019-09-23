package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularTvShowsInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.ReactiveRetrieveInteractor<GetPopularTvShowsInteractor.PopularTvShowsParams, PagedTvShows> {

    override fun execute(params: PopularTvShowsParams): Observable<PagedTvShows> {
        require(params.page > 0) { "Invalid current page number" }
        check(params.page <= params.totalPage) { "No more pages available!" }
        return tvShowsRepository.getPopularTShows(params.page).toObservable()
    }

    class PopularTvShowsParams(val page: Int, val totalPage: Int) : Interactor.Params()
}