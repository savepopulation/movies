package com.raqun.movies.shows.domain

import com.raqun.movies.shows.domain.util.DataProvider
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import java.io.IOException

class GetPopularTvShowsInteractorTest {

    private var tvShowsRepository = Mockito.mock(TvShowsRepository::class.java)

    private val popularTvShowsInteractor by lazy {
        GetPopularTvShowsInteractor(tvShowsRepository)
    }

    /*
     * Tests successful response
     */
    @Test
    fun testPopularTvShowsInteractor_getPopularTvShows_Success() {
        val currentPage = 1
        val totalPage = 2
        val pageResultCount = 10
        val totalResults = totalPage * pageResultCount
        val params = GetPopularTvShowsInteractor.PopularTvShowsParams(currentPage, totalPage)

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

        popularTvShowsInteractor.execute(params)
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .dispose()
    }

    /*
     * Test failed response
     */
    @Test
    fun testPopularTvShowsInteractor_getPopularTvShows_Fail() {
        val errorResponse = IOException("Failed to connect to network!")
        val currentPage = 1
        val totalPage = 2
        val params = GetPopularTvShowsInteractor.PopularTvShowsParams(currentPage, totalPage)

        Mockito.`when`(tvShowsRepository.getPopularTShows(anyInt()))
            .thenReturn(Single.error(errorResponse))

        popularTvShowsInteractor.execute(params)
            .test()
            .assertSubscribed()
            .assertError(errorResponse)
            .dispose()
    }

    /*
     * Test if page number is unexpected or not
     */
    @Test
    fun testPopularTvShowsInteractor_getPopularTvShows_Fail_UnexpectedCurrentPageNumber() {
        val currentPage = 0
        val totalPage = 2
        val params = GetPopularTvShowsInteractor.PopularTvShowsParams(currentPage, totalPage)

        var result: java.lang.IllegalArgumentException? = null
        try {
            popularTvShowsInteractor.execute(params)
                .test()
        } catch (e: java.lang.IllegalArgumentException) {
            result = e
        }

        assert(result != null)
    }

    /*
     * Test if trying to reach above total page number
     */
    @Test
    fun testPopularTvShowsInteractor_getPopularTvShows_Fail_UnexpectedCurrentPageRange() {
        val currentPage = 3
        val totalPage = 2
        val params = GetPopularTvShowsInteractor.PopularTvShowsParams(currentPage, totalPage)

        var result: java.lang.IllegalStateException? = null
        try {
            popularTvShowsInteractor.execute(params)
                .test()
        } catch (e: java.lang.IllegalStateException) {
            result = e
        }

        assert(result != null)
    }
}