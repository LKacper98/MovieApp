package com.kacper.movieapp.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.kacper.movieapp.local.FavoriteMovieRepository

class FavoriteViewModel @ViewModelInject constructor(
        repository: FavoriteMovieRepository
) : ViewModel() {
    val movies = repository.getFavoriteMovies()
}