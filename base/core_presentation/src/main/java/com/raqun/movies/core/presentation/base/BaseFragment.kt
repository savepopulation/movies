package com.raqun.movies.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.raqun.movies.core.error.Error
import com.raqun.movies.core.presentation.Constants
import com.raqun.movies.core.presentation.R
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseFragment : Fragment(), BaseView {

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @MenuRes
    open val menuRes = Constants.NO_RES

    @StringRes
    open val titleRes = Constants.NO_RES

    override fun onAttach(context: Context?) {
        if (activity is HasSupportFragmentInjector) {
            AndroidSupportInjection.inject(this)
            onInjected()
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuRes != Constants.NO_RES)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menuRes != Constants.NO_RES) {
            inflater?.inflate(menuRes, menu)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (titleRes != Constants.NO_RES) {
            setActivityTitle(getString(titleRes))
        }
    }

    override fun onError(e: Error) {
        val message = when (e) {
            is Error.UnknownError -> getString(R.string.error_message_unknowon_error)
            is Error.BusinessError -> e.message
            is Error.ApiError -> e.message
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    protected fun setActivityTitle(title: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setScreenTitle(title)
        }
    }

    fun getApplication() = activity?.application

    fun getApplicationContext() = getApplication()?.applicationContext

    open fun initView() {
        // can be overridden
    }

    open fun onInjected() {
        // can be overridden
    }
}