package com.dapursegar.app.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dapursegar.app.core.database.dao.MovieDao


/**
 * Created by Ramana on 05-Oct-19.
 */

//@Database(
//    entities = [],
//    version = 1,
//    exportSchema = false
//)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        @JvmStatic
        fun init(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "dapursegar_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun movideDao(): MovieDao
}