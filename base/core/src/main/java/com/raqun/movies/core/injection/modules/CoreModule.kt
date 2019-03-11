package com.raqun.movies.core.injection.modules

import com.raqun.movies.core.error.DefaultErrorFactory
import com.raqun.movies.core.error.ErrorFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    internal fun provideErrorFactory(): ErrorFactory = DefaultErrorFactory()

}