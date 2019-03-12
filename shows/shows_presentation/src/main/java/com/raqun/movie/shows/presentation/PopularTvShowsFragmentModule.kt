package com.raqun.movie.shows.presentation

import com.raqun.movies.core.injection.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PopularTvShowsFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [PopularTvShowsPresentationModule::class])
    abstract fun contributeShowsFragmentInjector(): PopularTvShowsFragment
}