package com.raqun.movies.ui

import android.os.Bundle
import com.raqun.movie.shows.presentation.PopularTvShowsFragment
import com.raqun.movies.R
import com.raqun.movies.core.presentation.base.BaseActivity
import com.raqun.movies.core.presentation.extensions.transact

class MainActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.transact {
                replace(R.id.framelayout_main, PopularTvShowsFragment.newInstance())
            }
        }
    }
}
