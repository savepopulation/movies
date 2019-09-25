package com.raqun.movies.injection.modules

import com.raqun.movie.shows.presentation.PopularTvShowsFragmentModule
import com.raqun.movies.core.injection.scope.ActivityScope
import com.raqun.movies.shows.details.presentation.TvShowDetailsActivityModule
import com.raqun.movies.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [TvShowDetailsActivityModule::class])
internal abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [PopularTvShowsFragmentModule::class])
    @ActivityScope
    abstract fun contributeMainActivityInjector(): MainActivity
}