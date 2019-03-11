package com.raqun.movies.core.data.api

data class ApiResponse<T>(val code: Int = 0, val message: String?, val data: T?)