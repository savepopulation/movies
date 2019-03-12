package com.raqun.movies.shows.data

import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShowsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ShowsDataModule {

    @Provides
    @Singleton
    fun provideShowsServices(retrofit: Retrofit): TvShowsServices = retrofit.create(TvShowsServices::class.java)

    @Provides
    @Singleton
    fun provideShowsRemoteDataSource(tvShowsServices: TvShowsServices): DataSource.RetrieveRemoteDataSource<Int, PagedTvShows> =
        PopularTvShowsRemoteDataSource(tvShowsServices)

    @Provides
    @Singleton
    fun provideShowsRepository(popularTvShowsRemoteDataSource: PopularTvShowsRemoteDataSource): TvShowsRepository =
        TvShowsRepositoryImpl(popularTvShowsRemoteDataSource)

}