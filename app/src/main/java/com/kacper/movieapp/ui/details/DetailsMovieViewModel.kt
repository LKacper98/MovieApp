package com.kacper.movieapp.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.kacper.movieapp.local.FavoriteMovieRepository
import com.kacper.movieapp.model.FavoriteMovie
import com.kacper.movieapp.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsMovieViewModel @ViewModelInject constructor(
        private val repository: FavoriteMovieRepository
) : ViewModel() {

    fun addToFavorite(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFavorite(
                    FavoriteMovie(
                            movie.id,
                            movie.original_title,
                            movie.overview,
                            movie.poster_path
                    )
            )
        }
    }

    suspend fun checkMovie(id: String) = repository.checkMovie(id)

    fun removeFromFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromFavorite(id)
        }
    }


}
