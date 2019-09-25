package com.raqun.movies.injection.modules

import androidx.lifecycle.ViewModelProvider
import com.raqun.movie.shows.presentation.PopularTvShowsViewModelModule
import com.raqun.movies.core.presentation.viewmodel.VmFactory
import com.raqun.movies.shows.details.presentation.TvShowDetailsViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [PopularTvShowsViewModelModule::class,
        TvShowDetailsViewModelModule::class]
)
internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}