package com.raqun.movies.core.data.injection.modules

import android.content.Context
import androidx.room.Room
import com.raqun.movies.core.data.db.Db
import com.raqun.movies.core.data.db.MoviesDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): MoviesDb = Room.databaseBuilder(
        context,
        MoviesDb::class.java, Db.Config.DB_NAME
    ).build()
}