package com.raqun.movies.ui

import android.os.Bundle
import com.raqun.movies.core.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
