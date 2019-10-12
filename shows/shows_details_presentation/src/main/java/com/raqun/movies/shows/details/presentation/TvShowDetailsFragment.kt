package com.raqun.movies.shows.details.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.navigation.features.TvShowDetails
import com.raqun.movies.core.presentation.Constants.Companion.IMAGE_BASE_URL
import com.raqun.movies.core.presentation.base.BaseViewModelFragment
import com.raqun.movies.core.presentation.extensions.loadImage
import com.raqun.movies.core.presentation.navigation.UiNavigation
import kotlinx.android.synthetic.main.fragment_show_details.*

class TvShowDetailsFragment : BaseViewModelFragment<TvShowDetailsViewModel>() {

    private var id: Int? = null

    override fun getModelClass() = TvShowDetailsViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_show_details

    override val uiNavigation = UiNavigation.BACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(TvShowDetails.BUNDLE_ID, 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.tvShowDetails.observe(this, Observer {
            when (it) {
                is DataHolder.Success -> {
                    toolbar.title = it.data.name
                    textViewOverView.text = it.data.overview
                    if (!it.data.posterPath.isNullOrEmpty()) {
                        imageViewPoster.loadImage(IMAGE_BASE_URL + it.data.posterPath!!)
                    }
                }

                is DataHolder.Fail -> {
                    // handle fail
                }

                is DataHolder.Loading -> {
                    // handle loading
                }
            }
        })

        id?.let {
            viewModel.getTvShowDetails(it)
        }
    }

    companion object {
        fun newInstance(id: Int?) = TvShowDetailsFragment().apply {
            val args = Bundle()
            if (id != null) {
                args.putInt(TvShowDetails.BUNDLE_ID, id)
            }
            arguments = args
        }
    }
}