package com.raqun.movies.injection.modules

import androidx.lifecycle.ViewModelProvider
import com.raqun.movies.core.presentation.viewmodel.VmFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}