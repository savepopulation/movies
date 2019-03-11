package com.raqun.movies.injection.components

import com.raqun.movies.MoviesApp
import com.raqun.movies.injection.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        DomainModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoviesApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: MoviesApp)
}