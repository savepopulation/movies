package com.raqun.movies.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raqun.movies.core.data.db.entity.TvShowEntity

@Dao
interface TvShowsDao {
    @Query("SELECT * FROM tv_shows ORDER BY popularity DESC LIMIT 20 OFFSET :page * 20")
    fun getTvShows(page: Int): List<TvShowEntity>

    @Query("SELECT * FROM tv_shows")
    fun getAllTvShows(): List<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShow(tvShowEntity: TvShowEntity): Long
}