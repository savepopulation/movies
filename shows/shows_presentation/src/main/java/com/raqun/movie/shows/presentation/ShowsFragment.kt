package com.raqun.movie.shows.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.presentation.base.BaseFragment
import javax.inject.Inject

class ShowsFragment : BaseFragment() {

    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: ShowsViewModel

    override fun getLayoutRes() = R.layout.fragment_shows

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(ShowsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.tvShows.observe(this, Observer {
            when (it) {
                is DataHolder.Success -> Log.e("result", "success")
                is DataHolder.Fail -> Log.e("result", "fail")
                is DataHolder.Loading -> Log.e("result", "loading")
            }
        })

        viewModel.getPopularTvShows()
    }

    companion object {
        fun newInstance() = ShowsFragment()
    }
}