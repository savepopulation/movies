package com.raqun.movie.shows.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.presentation.base.BaseFragment
import javax.inject.Inject

class PopularTvShowsFragment : BaseFragment() {

    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory

    protected lateinit var viewModelPopularTv: PopularTvShowsViewModel

    override fun getLayoutRes() = R.layout.fragment_shows

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelPopularTv = ViewModelProviders.of(this, vmFactory).get(PopularTvShowsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelPopularTv.tvShows.observe(this, Observer {
            when (it) {
                is DataHolder.Success -> Log.e("result", "success")
                is DataHolder.Fail -> Log.e("result", "fail")
                is DataHolder.Loading -> Log.e("result", "loading")
            }
        })

        viewModelPopularTv.getPopularTvShows()
    }

    companion object {
        fun newInstance() = PopularTvShowsFragment()
    }
}