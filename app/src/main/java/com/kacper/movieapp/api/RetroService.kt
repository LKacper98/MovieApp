package com.kacper.movieapp.api

import com.kacper.movieapp.responses.MovieResponse
import com.kacper.movieapp.responses.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(@Query("page") position: Int): MovieResponse

    @GET("person/popular?api_key=$API_KEY")
    suspend fun getNowPerson(@Query("page") position: Int): PersonResponse

    @GET("search/movie?api_key=$API_KEY")
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): MovieResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "da4c8615682442891a5d9ba54c30654b"
    }
}