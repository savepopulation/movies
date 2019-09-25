package com.raqun.movies.shows.data

import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShowDetail
import com.raqun.movies.shows.domain.TvShowsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ShowsDataModule {

    @Provides
    @Singleton
    fun provideShowsServices(retrofit: Retrofit): TvShowsServices =
        retrofit.create(TvShowsServices::class.java)

    @Provides
    fun provideGetPopularTvShowsRemoteDataSource(tvShowsServices: TvShowsServices): DataSource.RetrieveRemoteDataSource<Int, PagedTvShows> =
        GetPopularTvShowsRemoteDataSource(tvShowsServices)

    @Provides
    fun provideGetTvShowDetailsRemoteDataSource(tvShowsServices: TvShowsServices): DataSource.RetrieveRemoteDataSource<Int, TvShowDetail> =
        GetTvShowDetailRemoteDataSource(tvShowsServices)

    @Provides
    @Singleton
    fun provideShowsRepository(
        getPopularTvShowsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>,
        getTvShowDetailRemoteDatASource: DataSource.RetrieveRemoteDataSource<Int, TvShowDetail>
    ): TvShowsRepository =
        TvShowsRepositoryImpl(getPopularTvShowsRemoteDataSource, getTvShowDetailRemoteDatASource)

}