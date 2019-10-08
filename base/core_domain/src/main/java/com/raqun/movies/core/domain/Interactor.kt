package com.raqun.movies.core.domain

import io.reactivex.Flowable
import io.reactivex.Observable

interface Interactor {

    interface ReactiveRetrieveInteractor<P : Params, R : Any> : Interactor {
        fun execute(params: P): Observable<R>
    }

    interface FlowableRetrieveInteractor<P : Params, R : Any> : Interactor {
        fun execute(params: P): Flowable<R>
    }
    // marker class
    abstract class Params
}