package com.raqun.movies.core.error

sealed class Error {

    class UnknownError : Error()

    data class ApiError(val code: Int = 1, val message: String? = null) : Error()

    data class BusinessError(val code: Int = 1, val message: String?) : Error()
}