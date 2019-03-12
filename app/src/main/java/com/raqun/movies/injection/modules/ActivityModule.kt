package com.raqun.movies.injection.modules

import com.raqun.movie.shows.presentation.PopularTvShowsFragmentModule
import com.raqun.movies.ui.MainActivity
import com.raqun.movies.core.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [PopularTvShowsFragmentModule::class])
    @ActivityScope
    abstract fun contributeMainActivityInjector(): MainActivity
}