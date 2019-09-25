package com.raqun.movies.shows.details.presentation

import androidx.lifecycle.ViewModel
import com.raqun.movies.core.presentation.viewmodel.ViewModelKey
import com.raqun.movies.shows.domain.TvShowDetail
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.functions.Function

@Module
abstract class TvShowDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TvShowDetailsViewModel::class)
    abstract fun bindTvShowDetailsViewModel(tvShowDetailsViewModel: TvShowDetailsViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provieTvShowDetailsViewEntityMapper(): Function<TvShowDetail, TvShowDetailsViewEntity> =
            TvShowDetailsViewEntityMapper()
    }
}