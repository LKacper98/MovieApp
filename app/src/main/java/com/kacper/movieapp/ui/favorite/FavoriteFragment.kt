package com.kacper.movieapp.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.kacper.movieapp.R
import com.kacper.movieapp.databinding.FragmentFavoriteBinding
import com.kacper.movieapp.model.FavoriteMovie
import com.kacper.movieapp.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        val adapter = FavoriteAdapter()

        viewModel.movies.observe(viewLifecycleOwner) {
            adapter.setMovieList(it)
            binding.apply {
                recyclerview_movie.setHasFixedSize(true)
                recyclerview_movie.adapter = adapter
            }
        }
        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClick(favoriteMovie: FavoriteMovie) {
                val movie = Movie(
                        favoriteMovie.id_movie,
                        favoriteMovie.overview,
                        favoriteMovie.poster_path,
                        favoriteMovie.original_title
                )
                val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
                findNavController().navigate(action)
            }

        })
    }
}