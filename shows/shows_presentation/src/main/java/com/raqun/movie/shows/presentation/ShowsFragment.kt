package com.raqun.movie.shows.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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

}