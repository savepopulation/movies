package com.raqun.movies

import android.app.Activity
import android.app.Application
import com.raqun.movies.injection.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MoviesApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector() = dispachingAndroidInjector
}