package com.raqun.movie.shows.presentation

import androidx.lifecycle.ViewModel
import com.raqun.movies.core.presentation.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ShowsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShowsViewModel::class)
    abstract fun bindShowsViewModel(showsViewModel: ShowsViewModel) : ViewModel
}