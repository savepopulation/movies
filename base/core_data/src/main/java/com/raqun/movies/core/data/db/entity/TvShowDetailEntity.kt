package com.raqun.movies.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raqun.movies.core.data.db.Db

@Entity(tableName = Db.TABLES.TVSHOW_DETAILS.NAME)
class TvShowDetailEntity constructor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = Db.TABLES.TVSHOW_DETAILS.COLUMNS.ID) val tvShowId: Long,
    @ColumnInfo(name = Db.TABLES.TVSHOW_DETAILS.COLUMNS.SHOW_NAME) val name: String,
    @ColumnInfo(name = Db.TABLES.TVSHOW_DETAILS.COLUMNS.POSTER_PATH) val posterPath: String,
    @ColumnInfo(name = Db.TABLES.TVSHOW_DETAILS.COLUMNS.VOTE_AVARAGE) val voteAvarage: Double,
    @ColumnInfo(name = Db.TABLES.TVSHOW_DETAILS.COLUMNS.OVERVIEW) val overview: String,
    @ColumnInfo(name = Db.TABLES.TVSHOW_DETAILS.COLUMNS.VOTE_COOUNT) val vodeCount: Int
) : DbEntity