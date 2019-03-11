package com.raqun.movies.core.data.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val CONTENT_TYPE = "Content-Type"
private const val JSON = "application/json"

class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(with(chain.request().newBuilder()) {
            addHeader(CONTENT_TYPE, JSON)
            build()
        })
    }
}