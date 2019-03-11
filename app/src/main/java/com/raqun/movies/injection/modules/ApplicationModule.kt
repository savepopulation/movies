package com.raqun.movies.injection.modules

import android.content.Context
import com.raqun.movies.MoviesApp
import com.raqun.movies.core.injection.modules.CoreModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [CoreModule::class]
)
class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: MoviesApp): Context {
        return app.applicationContext
    }
}