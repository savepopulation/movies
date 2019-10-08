package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import io.reactivex.Flowable
import javax.inject.Inject

class GetPopularTvShowsInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.FlowableRetrieveInteractor<GetPopularTvShowsInteractor.Params, PagedTvShows> {

    override fun execute(params: Params): Flowable<PagedTvShows> {
        require(params.page > 0) { "Invalid current page number" }
        check(params.page <= params.totalPage) { "No more pages available!" }
        return tvShowsRepository.getPopularTShows(params.page)
    }

    class Params(val page: Int, val totalPage: Int) : Interactor.Params()
}