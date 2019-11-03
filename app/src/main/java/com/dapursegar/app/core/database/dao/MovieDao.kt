package com.dapursegar.app.core.database.dao

import androidx.room.Dao

@Dao
interface MovieDao {

//    @Query("SELECT * FROM movie")
//    fun getAllMoviesWithCursor(): Cursor
//
//    @Query("SELECT * FROM movie WHERE _id = :movieId")
//    fun getByIdWithCursor(movieId: Long): Cursor
//
//    @Query("SELECT * FROM movie")
//    fun getAll(): MutableList<MovieItem>
//
//    @Query("SELECT * FROM movie WHERE _id = :movieId")
//    fun getById(movieId: Int): MovieItem
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(movieItem: MovieItem): Long
//
//    @Update
//    fun update(movieItem: MovieItem)
//
//    @Delete
//    fun delete(movieItem: MovieItem)

    companion object {

//        fun getAll(): MutableList<MovieItem>? {
//            return MovieApp.getInstance()?.getDatabase()?.movideDao()?.getAllMovies()
//        }
//
//        fun insert(movieItem: MovieItem): Long? {
//            return MovieApp.getInstance()?.getDatabase()?.movideDao()?.insertMovie(movieItem)
//        }
//
//        fun update(movieItem: MovieItem) {
//            MovieApp.getInstance()?.getDatabase()?.movideDao()?.updateMovie(movieItem)
//        }
//
//        fun delete(movieItem: MovieItem) {
//            MovieApp.getInstance()?.getDatabase()?.movideDao()?.deleteMovie(movieItem)
//        }
    }
}