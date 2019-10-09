package com.raqun.movies.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raqun.movies.core.data.db.Db

@Entity(tableName = Db.TABLES.TVSHOWS.NAME)
class TvShowEntity constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = Db.TABLES.TVSHOWS.COLUMNS.ID) val tvShowId: Long,
    @ColumnInfo(name = Db.TABLES.TVSHOWS.COLUMNS.SHOW_NAME) val name: String,
    @ColumnInfo(name = Db.TABLES.TVSHOWS.COLUMNS.POSTER_PATH) val posterPath: String,
    @ColumnInfo(name = Db.TABLES.TVSHOWS.COLUMNS.VOTE_AVARAGE) val voteAvarage: Double,
    @ColumnInfo(name = Db.TABLES.TVSHOWS.COLUMNS.POPULARITY) val popularity: Int
) : DbEntity