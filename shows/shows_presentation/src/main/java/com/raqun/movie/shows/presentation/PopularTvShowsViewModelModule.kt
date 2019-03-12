package com.raqun.movie.shows.presentation

import androidx.lifecycle.ViewModel
import com.raqun.movies.core.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PopularTvShowsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PopularTvShowsViewModel::class)
    abstract fun bindShowsViewModel(popularTvShowsViewModel: PopularTvShowsViewModel) : ViewModel
}