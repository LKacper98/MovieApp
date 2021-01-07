package com.kacper.movieapp.module

import android.content.Context
import androidx.room.Room
import com.kacper.movieapp.api.RetroService
import com.kacper.movieapp.local.FavoriteMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFavMovieDatabase(
            @ApplicationContext app: Context
    ) = Room.databaseBuilder(
            app,
            FavoriteMovieDatabase::class.java,
            "movie_db"
    ).build()

    @Singleton
    @Provides
    fun provideFavMovieDao(db: FavoriteMovieDatabase) = db.getFavoriteMovieDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
                .baseUrl(RetroService.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): RetroService =
            retrofit.create(RetroService::class.java)
}