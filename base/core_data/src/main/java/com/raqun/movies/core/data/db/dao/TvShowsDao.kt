package com.raqun.movies.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raqun.movies.core.data.db.entity.TvShowEntity
import io.reactivex.Flowable

@Dao
interface TvShowsDao {
    @Query("SELECT * FROM tv_shows")
    fun getTvShows(page: Int): Flowable<TvShowEntity>

    @Query("SELECT * FROM tv_shows")
    fun getAllTvShows(): Flowable<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShow(tvShowEntity: TvShowEntity): Long
}