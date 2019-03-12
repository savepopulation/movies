package com.raqun.movies.core.presentation.entity

import io.reactivex.functions.Function

interface ViewEntityMapper<T : Any?, R : ViewEntity> : Function<T, R>