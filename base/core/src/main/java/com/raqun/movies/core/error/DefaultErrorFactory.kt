package com.raqun.movies.core.error

class DefaultErrorFactory : ErrorFactory {

    override fun createUnknownError() = Error.UnknownError()

    override fun createApiError(code: Int, message: String?) = Error.ApiError(code, message)

    override fun createErrorFromThrowable(t: Throwable) = Error.UnknownError()

    override fun createBusinessError(code: Int, message: String?) = Error.BusinessError(code, message)
}