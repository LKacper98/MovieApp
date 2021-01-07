package com.kacper.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.kacper.movieapp.api.RetroService
import com.kacper.movieapp.paging.MoviePagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val retroService: RetroService) {

    fun getNowPlayingMovies() =
            Pager(config = PagingConfig(
                    pageSize = 5,
                    maxSize = 20,
                    enablePlaceholders = false),
                    pagingSourceFactory = {
                        MoviePagingSource(
                                retroService,
                                null)
                    }
            ).liveData

    fun getSearchMovies(query: String) =
            Pager(config = PagingConfig(
                    pageSize = 5,
                    maxSize = 20,
                    enablePlaceholders = false),
                    pagingSourceFactory = {
                        MoviePagingSource(
                                retroService,
                                query)
                    }
            ).liveData
}