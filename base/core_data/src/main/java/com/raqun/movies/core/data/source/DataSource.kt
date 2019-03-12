package com.raqun.movies.core.data.source

import com.raqun.movies.core.data.api.PagedApiResponse
import com.raqun.movies.core.model.DataHolder
import io.reactivex.Observable
import io.reactivex.Single

interface DataSource {

    interface RetrieveRemoteDataSource<Req, Res : Any> : DataSource {
        fun getResult(request: Req): Single<Res>
    }

}
