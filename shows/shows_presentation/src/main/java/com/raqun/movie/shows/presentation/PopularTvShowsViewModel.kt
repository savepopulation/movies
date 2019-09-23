package com.raqun.movie.shows.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raqun.movies.core.domain.Interactor
import com.raqun.movies.core.error.ErrorFactory
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.presentation.recyclerview.DisplayItem
import com.raqun.movies.shows.domain.PagedTvShows
import com.raqun.movies.shows.domain.GetPopularTvShowsInteractor
import com.raqun.movies.shows.domain.TvShow
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PopularTvShowsViewModel @Inject constructor(
    private val getPopularTvShowsInteractor: Interactor.ReactiveRetrieveInteractor<GetPopularTvShowsInteractor.PopularTvShowsParams, PagedTvShows>,
    private val itemMapper: Function<TvShow, DisplayItem>,
    private val errorFactory: ErrorFactory
) : ViewModel() {

    private val _popularTvShowsLiveData = MediatorLiveData<DataHolder<List<DisplayItem>>>()
    private val _pageLiveData = MutableLiveData<Int>()
    private val compositeDisposable = CompositeDisposable()
    private val popularTvShows = ArrayList<DisplayItem>()
    private val page = Page()

    val popularTvShowsLiveData: LiveData<DataHolder<List<DisplayItem>>>
        get() = _popularTvShowsLiveData

    init {
        _popularTvShowsLiveData.value = DataHolder.Success(popularTvShows)
        _popularTvShowsLiveData.addSource(_pageLiveData) {
            fetchPopularTvShows(it)
        }
    }

    fun getPopularTvShowsByPagination() {
        if (_pageLiveData.value == null) {
            _pageLiveData.value = page.currentPage
        } else {
            val nextPage = page.currentPage + 1
            if (nextPage > page.totalPages) {
                return
            }
            _pageLiveData.value = nextPage
        }
    }

    fun refreshPopularTvShows() {
        page.currentPage = 0
        getPopularTvShowsByPagination()
    }

    @SuppressLint("CheckResult")
    private fun fetchPopularTvShows(currentPage: Int) {
        _popularTvShowsLiveData.value = DataHolder.Loading()
        val pagedParams = GetPopularTvShowsInteractor.PopularTvShowsParams(currentPage, page.totalPages)
        val popularTvShowsFetchDisposible = getPopularTvShowsInteractor.execute(pagedParams)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .subscribe({
                this.page.currentPage = it.page
                this.page.totalPages = it.totalPages

                Observable.fromIterable(it.results)
                    .map { item -> itemMapper.apply(item) }
                    .toList()
                    .blockingGet()
                    .run {
                        _popularTvShowsLiveData.postValue(DataHolder.Success(this))
                        popularTvShows.addAll(this)
                    }
            }, {
                _popularTvShowsLiveData.postValue(DataHolder.Fail(errorFactory.createErrorFromThrowable(it)))
            })
        compositeDisposable.add(popularTvShowsFetchDisposible!!)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    data class Page(var currentPage: Int = 1, var totalPages: Int = 1)
}