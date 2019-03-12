package com.raqun.movie.shows.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.presentation.base.BaseFragment
import com.raqun.movies.core.presentation.extensions.setup
import com.raqun.movies.core.presentation.recyclerview.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_popular_tv_shows.*
import javax.inject.Inject

class PopularTvShowsFragment : BaseFragment() {

    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory

    @Inject
    protected lateinit var popularTvShowsAdapter: RecyclerViewAdapter

    protected lateinit var viewModelPopularTv: PopularTvShowsViewModel

    override fun getLayoutRes() = R.layout.fragment_popular_tv_shows

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelPopularTv = ViewModelProviders.of(this, vmFactory).get(PopularTvShowsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewPopularTvShows.setup(context = activity!!, adapter = popularTvShowsAdapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelPopularTv.popularTvShowsLiveData.observe(this, Observer {
            when (it) {
                is DataHolder.Success -> popularTvShowsAdapter.update(it.data)
                is DataHolder.Fail -> Log.e("result", "fail")
                is DataHolder.Loading -> Log.e("result", "loading")
            }
        })

        viewModelPopularTv.getPopularTvShowsByPagination()
    }

    companion object {
        fun newInstance() = PopularTvShowsFragment()
    }
}