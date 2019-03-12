package com.raqun.movies.core.presentation.base

import com.raqun.movies.core.error.Error

interface BaseView {
    fun onError(e: Error)
}