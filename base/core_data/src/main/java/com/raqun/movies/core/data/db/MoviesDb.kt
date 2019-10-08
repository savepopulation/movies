package com.raqun.movies.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raqun.movies.core.data.db.dao.TvShowDetailsDao
import com.raqun.movies.core.data.db.dao.TvShowsDao
import com.raqun.movies.core.data.db.entity.TvShowDetailEntity
import com.raqun.movies.core.data.db.entity.TvShowEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [TvShowEntity::class, TvShowDetailEntity::class],
    version = Db.Config.DB_VERSION,
    exportSchema = true
)
abstract class MoviesDb : RoomDatabase() {
    abstract fun tvShowsDao(): TvShowsDao

    abstract fun tvShowDetailsDao(): TvShowDetailsDao
}