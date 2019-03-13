package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import io.reactivex.Observable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class PopularTvShowsInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.ReactiveRetrieveInteractor<PopularTvShowsInteractor.PopularTvShowsParams, PagedTvShows> {

    override fun execute(params: PopularTvShowsParams): Observable<PagedTvShows> {
        if (params.page <= 0) {
            throw IllegalArgumentException("Invalid current page number")
        }

        if (params.page > params.totalPage) {
            throw IllegalStateException("No more pages available!")
        }

        return tvShowsRepository.getPopularTShows(params.page).toObservable()
    }

    class PopularTvShowsParams(val page: Int, val totalPage: Int) : Interactor.Params()
}