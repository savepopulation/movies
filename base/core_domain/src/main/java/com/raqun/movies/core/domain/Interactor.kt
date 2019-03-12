package com.raqun.movies.core.domain

import com.raqun.movies.core.model.DataHolder
import io.reactivex.Observable
import io.reactivex.Single

interface Interactor {

    interface ReactiveRetrieveInteractor<P : Params, R : Any> : Interactor {
        fun execute(params: P): Observable<R>
    }

    // marker class
    abstract class Params
}