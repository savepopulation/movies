package com.raqun.movies.shows.details.presentation

import android.os.Bundle
import com.raqun.movies.core.navigation.features.TvShowDetails
import com.raqun.movies.core.presentation.BaseInjectionActivity
import com.raqun.movies.core.presentation.extensions.transact

class TvShowDetailsActivity : BaseInjectionActivity() {

    override fun getLayoutRes() = R.layout.activity_show_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.transact {
                replace(
                    R.id.framelayout_main,
                    TvShowDetailsFragment.newInstance(
                        intent?.getIntExtra(
                            TvShowDetails.BUNDLE_ID,
                            0
                        )
                    )
                )
            }
        }
    }
}