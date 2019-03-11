package com.raqun.movies.injection.modules

import com.raqun.movies.shows.domain.ShowsDomainModule
import dagger.Module

@Module(
    includes = [ShowsDomainModule::class]
)
internal abstract class DomainModule