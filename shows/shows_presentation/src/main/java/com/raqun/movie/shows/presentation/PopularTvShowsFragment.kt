package com.raqun.movie.shows.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    protected lateinit var viewModelPopularTvShows: PopularTvShowsViewModel

    override fun getLayoutRes() = R.layout.fragment_popular_tv_shows

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = recyclerViewPopularTvShows.childCount
            val totalItemCount = recyclerViewPopularTvShows.layoutManager?.itemCount ?: 0
            val firstVisibleItemPosition =
                (recyclerViewPopularTvShows.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if (!(viewModelPopularTvShows.popularTvShowsLiveData.value is DataHolder.Loading)) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 5
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModelPopularTvShows.getPopularTvShowsByPagination()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelPopularTvShows = ViewModelProviders.of(this, vmFactory).get(PopularTvShowsViewModel::class.java)
    }

    override fun initView() {
        recyclerViewPopularTvShows.apply {
            setup(context = activity!!, adapter = popularTvShowsAdapter)
            addOnScrollListener(recyclerViewOnScrollListener)
        }
        swipteToRefreshLayoutShows.setOnRefreshListener {
            viewModelPopularTvShows.refreshPopularTvShows()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelPopularTvShows.popularTvShowsLiveData.observe(this, Observer {
            swipteToRefreshLayoutShows.isRefreshing = it is DataHolder.Loading
            when (it) {
                is DataHolder.Success -> popularTvShowsAdapter.addItems(it.data)
                is DataHolder.Fail -> onError(it.e)
                is DataHolder.Loading -> Log.e("loading", "tv shows")
            }
        })

        if (savedInstanceState == null) {
            viewModelPopularTvShows.getPopularTvShowsByPagination()
        }
    }

    companion object {
        fun newInstance() = PopularTvShowsFragment()
    }
}