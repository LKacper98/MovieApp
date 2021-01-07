package com.kacper.movieapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kacper.movieapp.R
import com.kacper.movieapp.databinding.ItemMovieBinding
import com.kacper.movieapp.model.Movie

class MovieAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie, listener: OnItemClickListener) {
            with(binding) {
                Glide.with(itemView)
                        .load("${movie.baseUrl}${movie.poster_path}")
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(ivMoviePoster)

                tvMovieTitle.text = movie.original_title
                root.setOnClickListener { listener?.onItemClick(movie) }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    companion object {
        private val DiffUtilCallBack = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }
}
