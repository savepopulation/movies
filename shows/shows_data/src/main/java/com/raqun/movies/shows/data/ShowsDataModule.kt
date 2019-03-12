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
    fun provideShowsServices(retrofit: Retrofit): ShowsServices = retrofit.create(ShowsServices::class.java)

    @Provides
    @Singleton
    fun provideShowsRemoteDataSource(showsServices: ShowsServices): DataSource.RetrieveRemoteDataSource<Int, PagedTvShows> =
        ShowsRemoteDataSource(showsServices)

    @Provides
    @Singleton
    fun provideShowsRepository(showsRemoteDataSource: ShowsRemoteDataSource): TvShowsRepository =
        TvShowsRepositoryImpl(showsRemoteDataSource)

}