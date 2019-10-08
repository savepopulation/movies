package com.raqun.movies.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raqun.movies.core.data.db.entity.TvShowDetailEntity

@Dao
interface TvShowDetailsDao {
    @Query("SELECT * FROM tv_show_details")
    fun getAllTvShows(): List<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTvShowDetail(tvShowDetailEntity: TvShowDetailEntity): Long
}