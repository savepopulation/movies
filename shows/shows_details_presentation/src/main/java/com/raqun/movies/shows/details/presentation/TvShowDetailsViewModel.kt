package com.raqun.movies.shows.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.raqun.movies.core.domain.Interactor
import com.raqun.movies.core.error.ErrorFactory
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.presentation.viewmodel.ReactiveViewModel
import com.raqun.movies.shows.domain.GetTvShowDetailInteractor
import com.raqun.movies.shows.domain.TvShowDetail
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowDetailsViewModel @Inject constructor(
    private val getTvShowDetailsViewInteractor: Interactor.ReactiveRetrieveInteractor<GetTvShowDetailInteractor.Params, TvShowDetail>,
    private val tvShowDetailsViewentityMapper: Function<TvShowDetail, TvShowDetailsViewEntity>,
    private val errorFactory: ErrorFactory
) : ReactiveViewModel() {

    private val _tvShowDetails = MediatorLiveData<DataHolder<TvShowDetailsViewEntity>>()
    val tvShowDetails: LiveData<DataHolder<TvShowDetailsViewEntity>>
        get() = _tvShowDetails

    fun getTvShowDetails(id: Int) {
        _tvShowDetails.value = DataHolder.Loading()
        val getTvShowDetailsParams = GetTvShowDetailInteractor.Params(id)
        val getTvShowsDisposable = getTvShowDetailsViewInteractor.execute(getTvShowDetailsParams)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _tvShowDetails.postValue(DataHolder.Success(tvShowDetailsViewentityMapper.apply(it)))
            }, {
                _tvShowDetails.postValue(
                    DataHolder.Fail(
                        errorFactory.createErrorFromThrowable(
                            it
                        )
                    )
                )
            })
        action(getTvShowsDisposable)
    }
}