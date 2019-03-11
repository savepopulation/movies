package com.raqun.movies.injection.modules

import androidx.lifecycle.ViewModelProvider
import com.raqun.movie.shows.presentation.ShowsViewModel
import com.raqun.movie.shows.presentation.ShowsViewModelModule
import com.raqun.movies.core.presentation.viewmodel.VmFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ShowsViewModelModule::class])
internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}