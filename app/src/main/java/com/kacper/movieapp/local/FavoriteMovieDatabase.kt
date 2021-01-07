package com.kacper.movieapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kacper.movieapp.model.FavoriteMovie

@Database(
        entities = [FavoriteMovie::class],
        version = 1
)

abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun getFavoriteMovieDao(): FavoriteMovieDao
}