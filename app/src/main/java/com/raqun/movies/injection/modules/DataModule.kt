package com.raqun.movies.injection.modules

import com.raqun.movies.core.data.injection.modules.ApiModule
import com.raqun.movies.shows.data.ShowsDataModule
import dagger.Module

@Module(
    includes = [ApiModule::class,
        ShowsDataModule::class]
)
internal abstract class DataModule