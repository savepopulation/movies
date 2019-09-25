package com.raqun.movies.shows.details.presentation

import com.raqun.movies.core.injection.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TvShowsDetailsFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeTvShowDetailsFragmentInjector(): TvShowDetailsFragment
}