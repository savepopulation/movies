package com.raqun.movies.shows.details.presentation

import com.raqun.movies.core.presentation.base.BaseViewModelFragment

class TvShowDetailsFragment : BaseViewModelFragment<TvShowDetailsViewModel>() {

    override fun getModelClass() = TvShowDetailsViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_show_details

}