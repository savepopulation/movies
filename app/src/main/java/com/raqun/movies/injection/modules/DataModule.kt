package com.raqun.movies.injection.modules

import com.raqun.movies.core.data.injection.modules.ApiModule
import com.raqun.movies.core.data.injection.modules.DbModule
import com.raqun.movies.shows.data.ShowsDataModule
import dagger.Module

@Module(
    includes = [ApiModule::class,
        DbModule::class,
        ShowsDataModule::class]
)
internal abstract class DataModule