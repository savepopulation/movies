package com.raqun.movies.core.data.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val CONTENT_TYPE = "Content-Type"
private const val JSON = "application/json"
private const val KEY_API = "api_key"

class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url()
            .newBuilder().addQueryParameter(KEY_API, ApiConstants.API_KEY)
            .build()

        return chain.proceed(with(chain.request().newBuilder()) {
            url(url)
            addHeader(CONTENT_TYPE, JSON)
            build()
        })
    }
}