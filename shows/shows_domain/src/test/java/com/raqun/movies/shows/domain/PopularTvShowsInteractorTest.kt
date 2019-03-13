package com.raqun.movies.shows.domain

import com.raqun.movies.shows.domain.util.DataProvider
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito

class PopularTvShowsInteractorTest {

    private var tvShowsRepository = Mockito.mock(TvShowsRepository::class.java)

    private val popularTvShowsInteractor by lazy {
        PopularTvShowsInteractor(tvShowsRepository)
    }

    @Test
    fun testPopularTvShowsInteractor_getPopularTvShows_Success() {
        val currentPage = 1
        val totalPage = 2
        val pageResultCount = 10
        val totalResults = totalPage * pageResultCount

        val successResponse = Single.just(
            PagedTvShows(
                currentPage,
                totalResults,
                totalPage,
                DataProvider.createDummyTvShows(pageResultCount)
            )
        )

        Mockito.`when`(tvShowsRepository.getPopularTShows(anyInt()))
            .thenReturn(successResponse)

        popularTvShowsInteractor.execute(PopularTvShowsInteractor.PopularTvShowsParams(currentPage, totalPage))
            .test()
            .assertSubscribed()
            .assertComplete()
    }
}