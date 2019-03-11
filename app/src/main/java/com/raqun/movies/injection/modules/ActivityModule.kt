package com.raqun.movies.injection.modules

import com.raqun.movies.MainActivity
import com.raqun.movies.core.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeMainActivityInjector(): MainActivity
}