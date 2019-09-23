package com.raqun.movies.shows.details.presentation

import androidx.lifecycle.ViewModel
import com.raqun.movies.core.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TvShowDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TvShowDetailsViewModel::class)
    abstract fun bindTvShowDetailsViewModel(popularTvShowsViewModel: TvShowDetailsViewModel): ViewModel
}