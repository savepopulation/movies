package com.raqun.movie.shows.presentation

import androidx.lifecycle.ViewModel
import com.raqun.movies.core.presentation.recyclerview.DisplayItem
import com.raqun.movies.core.presentation.viewmodel.ViewModelKey
import com.raqun.movies.shows.domain.TvShow
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.functions.Function

@Module
abstract class PopularTvShowsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PopularTvShowsViewModel::class)
    abstract fun bindShowsViewModel(popularTvShowsViewModel: PopularTvShowsViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun proviePopularTvShowsViewEntityMapper(): Function<TvShow, DisplayItem> = PopularTvShowsViewEntityMapper()
    }
}