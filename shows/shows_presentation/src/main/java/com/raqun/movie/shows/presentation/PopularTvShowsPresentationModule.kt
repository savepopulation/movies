package com.raqun.movie.shows.presentation

import com.raqun.movie.shows.presentation.PopularTvShowsPresentationConstants.TYPES.SHOW
import com.raqun.movies.core.presentation.recyclerview.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class PopularTvShowsPresentationModule {

    @Binds
    @IntoMap
    @IntKey(SHOW)
    internal abstract fun bindPopularTvShowsViewHolderFactory(viewHolderFactory: PopularTvShowsViewHolder.PopularTvShowsViewHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(SHOW)
    internal abstract fun bindPopularTvShowsViewHolderBinder(viewHolderBinder: PopularTvShowsViewHolder.PopularTvShowsViewHolderBinder): ViewHolderBinder

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideDisplayItemComperator(): DisplayItemComperator = DefaultDisplayItemComperator()

        @JvmStatic
        @Provides
        fun provideRecyclerAdapter(
            itemComparator: DisplayItemComperator,
            factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
            binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder>
        ): RecyclerViewAdapter {
            return RecyclerViewAdapter(
                itemComperator = itemComparator,
                viewHolderFactoryMap = factoryMap,
                viewBinderFactoryMap = binderMap
            )
        }
    }
}