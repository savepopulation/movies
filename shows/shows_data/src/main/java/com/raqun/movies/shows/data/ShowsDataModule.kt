package com.raqun.movies.shows.data

import com.raqun.movies.core.data.db.MoviesDb
import com.raqun.movies.core.data.db.entity.TvShowEntity
import com.raqun.movies.core.data.source.DataSource
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.TvShow
import com.raqun.movies.shows.domain.TvShowDetail
import com.raqun.movies.shows.domain.TvShowsRepository
import dagger.Module
import dagger.Provides
import io.reactivex.functions.Function
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
    fun provideShowsLocalDataSource(
        db: MoviesDb, tvShowMapper: Function<TvShowEntity, TvShow>,
        tvShowEntityMapper: Function<TvShow, TvShowEntity>
    ): DataSource.FlowableLocal<Int, List<TvShow>> =
        TvShowsLocalDataSource(db, tvShowMapper, tvShowEntityMapper)

    @Provides
    fun provideTvShowEntityMapper(): Function<TvShow, TvShowEntity> = TvShowEntityMapper()

    @Provides
    fun provideTvShowMapper(): Function<TvShowEntity, TvShow> = TvShowMapper()

    @Provides
    @Singleton
    fun provideShowsRepository(
        getPopularTvShowsRemoteDataSource: DataSource.RetrieveRemoteDataSource<Int, PagedTvShows>,
        getTvShowDetailRemoteDatASource: DataSource.RetrieveRemoteDataSource<Int, TvShowDetail>,
        tvShowsLocalDataSource: DataSource.FlowableLocal<Int, List<TvShow>>
    ): TvShowsRepository =
        TvShowsRepositoryImpl(
            getPopularTvShowsRemoteDataSource,
            getTvShowDetailRemoteDatASource,
            tvShowsLocalDataSource
        )

}