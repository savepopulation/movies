package com.raqun.movies.shows.domain

import com.raqun.movies.core.domain.Interactor
import dagger.Module
import dagger.Provides

@Module
class ShowsDomainModule {

    @Provides
    fun providePopularTvShowsInteractor(tvShowsRepository: TvShowsRepository):
            Interactor.ReactiveRetrieveInteractor<GetPopularTvShowsInteractor.Params, PagedTvShows> =
        GetPopularTvShowsInteractor(tvShowsRepository)

}