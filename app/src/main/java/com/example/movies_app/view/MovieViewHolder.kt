package com.example.movies_app.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_app.databinding.ItemMovieBinding
import com.example.movies_app.model.Movie
import com.example.movies_app.network.ApiService
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun bind(movieItemResponse: Movie, onItemSelected: (String) -> Unit) {
        binding.movieTitle.text = movieItemResponse.title
        binding.voteAverage.text = movieItemResponse.vote_average.toString()
        val urlImage = ApiService.URL_IMAGES + movieItemResponse.poster_path
        Picasso.get().load(urlImage).into(binding.posterPath)
        binding.root.setOnClickListener { onItemSelected(movieItemResponse.movieId.toString()) }
    }
}