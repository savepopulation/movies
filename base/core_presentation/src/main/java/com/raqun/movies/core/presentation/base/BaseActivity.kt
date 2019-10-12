package com.raqun.movies.core.presentation.base

import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.raqun.movies.core.presentation.Constants
import com.raqun.movies.core.presentation.R
import com.raqun.movies.core.presentation.navigation.UiNavigation
import dagger.android.support.HasSupportFragmentInjector

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @StringRes
    open val titleRes = R.string.app_name

    @MenuRes
    open val menuRes = Constants.NO_RES

    open val uiNavigation = UiNavigation.BACK

    @IdRes
    open val toolbarRes = Constants.NO_RES

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        if (toolbarRes != Constants.NO_RES) {
            setToolbar(findViewById(toolbarRes))
        }
        initNavigation(uiNavigation)
        setScreenTitle(getString(titleRes))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menuRes != Constants.NO_RES) {
            menuInflater.inflate(menuRes, menu)
            return true
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    fun setScreenTitle(@StringRes titleRes: Int) {
        var title: String? = null
        try {
            title = getString(titleRes)
        } catch (e: Resources.NotFoundException) {
            // ignored
        }
        setScreenTitle(title)
    }

    fun setScreenTitle(title: String?) {
        supportActionBar?.title = title
    }

    fun setToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
    }

    fun setNavigation(uiNavigation: UiNavigation) {
        initNavigation(uiNavigation)
    }

    fun initNavigation(uiNavigation: UiNavigation) {
        when (uiNavigation) {
            UiNavigation.BACK -> {
                supportActionBar?.apply {
                    setDisplayShowHomeEnabled(true)
                    setHomeButtonEnabled(true)
                    setDisplayHomeAsUpEnabled(true)
                }

            }
            UiNavigation.ROOT -> {
                supportActionBar?.apply {
                    setDisplayShowHomeEnabled(false)
                    setHomeButtonEnabled(false)
                    setDisplayHomeAsUpEnabled(false)
                }
            }
            UiNavigation.NONE -> {
                // no-op
            }
        }
    }
}