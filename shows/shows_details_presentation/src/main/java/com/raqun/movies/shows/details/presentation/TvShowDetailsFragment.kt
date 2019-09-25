package com.raqun.movies.shows.details.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import com.raqun.movies.core.model.DataHolder
import com.raqun.movies.core.navigation.features.TvShowDetails
import com.raqun.movies.core.presentation.base.BaseViewModelFragment
import kotlinx.android.synthetic.main.fragment_show_details.*

class TvShowDetailsFragment : BaseViewModelFragment<TvShowDetailsViewModel>() {

    private var id: Int? = null

    override fun getModelClass() = TvShowDetailsViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_show_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(TvShowDetails.BUNDLE_ID, 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.tvShowDetails.observe(this, Observer {
            when (it) {
                is DataHolder.Success -> {
                    textViewTitle.text = it.data.name
                    textViewOverView.text = it.data.overview
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