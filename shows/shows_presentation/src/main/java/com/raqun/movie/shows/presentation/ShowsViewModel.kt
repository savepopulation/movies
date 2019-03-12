package com.raqun.movie.shows.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.raqun.movies.core.domain.Interactor
import com.raqun.movies.core.error.ErrorFactory
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.shows.domain.PagedTvShow
import com.raqun.movies.shows.domain.PopularTvShowsInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShowsViewModel @Inject constructor(
    private val popularTvShowsInteractor: Interactor.ReactiveRetrieveInteractor<PopularTvShowsInteractor.PopularTvShowsParams, PagedTvShow>,
    private val errorFactory: ErrorFactory
) : ViewModel() {

    private val _tvShows = MediatorLiveData<DataHolder<PagedTvShow>>()
    private val _page = MutableLiveData<Int>()
    private val compositeDisposable = CompositeDisposable()

    val tvShows: MediatorLiveData<DataHolder<PagedTvShow>>
        get() = _tvShows

    val page: MutableLiveData<Int>
        get() = _page

    init {
        tvShows.addSource(_page) {
            fetchPopularTvShows(it)
        }
    }

    fun getPopularTvShows() {
        if (_page.value == null) {
            _page.value = 1
        } else {
            _page.value = _page.value as Int + 1
        }
    }

    @SuppressLint("CheckResult")
    private fun fetchPopularTvShows(page: Int) {
        _tvShows.value = DataHolder.Loading()
        val pagedParams = PopularTvShowsInteractor.PopularTvShowsParams(page)
        val popularTvShowsFetchDisposible = popularTvShowsInteractor.execute(pagedParams)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
                _tvShows.value = DataHolder.Success(it)
            }, {
                it.printStackTrace()
                _tvShows.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
            })
        compositeDisposable.add(popularTvShowsFetchDisposible!!)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}