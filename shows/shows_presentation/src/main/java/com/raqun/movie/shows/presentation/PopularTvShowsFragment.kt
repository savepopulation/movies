package com.raqun.movie.shows.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.navigation.features.TvShowDetails
import com.raqun.movies.core.presentation.base.BaseViewModelFragment
import com.raqun.movies.core.presentation.extensions.setup
import com.raqun.movies.core.presentation.recyclerview.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_popular_tv_shows.*
import javax.inject.Inject


class PopularTvShowsFragment : BaseViewModelFragment<PopularTvShowsViewModel>() {

    @Inject
    protected lateinit var popularTvShowsAdapter: RecyclerViewAdapter

    override fun getLayoutRes() = R.layout.fragment_popular_tv_shows

    override fun getModelClass() = PopularTvShowsViewModel::class.java

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = recyclerViewPopularTvShows.childCount
            val totalItemCount = recyclerViewPopularTvShows.layoutManager?.itemCount ?: 0
            val firstVisibleItemPosition =
                (recyclerViewPopularTvShows.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if (viewModel.popularTvShowsLiveData.value !is DataHolder.Loading) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 5
                    && firstVisibleItemPosition >= 0
                ) {
                    viewModel.getPopularTvShowsByPagination()
                }
            }
        }
    }

    override fun initView() {
        recyclerViewPopularTvShows.apply {
            setup(context = activity!!, adapter = popularTvShowsAdapter)
            addOnScrollListener(recyclerViewOnScrollListener)
        }
        swipteToRefreshLayoutShows.setOnRefreshListener {
            viewModel.refreshPopularTvShows()
        }
        popularTvShowsAdapter.itemClickListener = {
            val showId = (it as? PopularTvShowViewEntity)?.id
            showId?.let {
                val intent = TvShowDetails.dynamicStart(showId)
                intent?.let {
                    startActivity(intent)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.popularTvShowsLiveData.observe(viewLifecycleOwner, Observer {
            swipteToRefreshLayoutShows.isRefreshing = it is DataHolder.Loading
            when (it) {
                is DataHolder.Success -> popularTvShowsAdapter.addItems(it.data)
                is DataHolder.Fail -> onError(it.e)
                is DataHolder.Loading -> Log.e("loading", "tv shows")
            }
        })

        if (savedInstanceState == null) {
            viewModel.getPopularTvShowsByPagination()
        }
    }

    companion object {
        fun newInstance() = PopularTvShowsFragment()
    }
}