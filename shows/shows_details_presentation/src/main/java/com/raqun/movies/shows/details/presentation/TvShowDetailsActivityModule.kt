package com.raqun.movies.shows.details.presentation

import com.raqun.movies.core.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TvShowDetailsActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [TvShowsDetailsFragmentModule::class])
    abstract fun provideTvShowsDetailsActivity(): TvShowDetailsActivity
}