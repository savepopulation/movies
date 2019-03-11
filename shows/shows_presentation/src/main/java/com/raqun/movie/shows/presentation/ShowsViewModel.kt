package com.raqun.movie.shows.presentation

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
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShowsViewModel @Inject constructor(
    private val popularTvShowsInteractor: Interactor.ReactiveRetrieveInteractor<PopularTvShowsInteractor.PopularTvShowsParams, PagedTvShow>,
    private val errorFactory: ErrorFactory
) : ViewModel() {

    private val _tvShows = MediatorLiveData<DataHolder<PagedTvShow>>()
    private val _page = MutableLiveData<Int>()

    val tvShows: MediatorLiveData<DataHolder<PagedTvShow>>
        get() = _tvShows

    init {
        tvShows.addSource(_page) {
            // get tv shows
        }
    }

    fun getPopularTvShows() {
        if (_page.value == null) {
            _page.value = 1
        } else {
            _page.value = _page.value as Int + 1
        }
    }
}