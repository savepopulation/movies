package com.raqun.movies.shows.data

class ApiException(val code: Int?, message: String?) : Exception(message)