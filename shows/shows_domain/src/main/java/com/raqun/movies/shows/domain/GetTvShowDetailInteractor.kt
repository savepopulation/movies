package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import io.reactivex.Observable
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetTvShowDetailInteractor @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    Interactor.ReactiveRetrieveInteractor<GetTvShowDetailInteractor.Params, TvShowDetail> {

    override fun execute(params: Params): Observable<TvShowDetail> {
        require(!(params.id == null || params.id <= 0)) { "Id cannot be null or negative!" }
        return tvShowsRepository.getShowDetail(params.id).toObservable()
    }

    class Params(val id: Int?) : Interactor.Params()
}