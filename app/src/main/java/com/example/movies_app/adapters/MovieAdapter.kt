package com.example.movies_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_app.R
import com.example.movies_app.model.Movie
import com.example.movies_app.view.MovieViewHolder

class MovieAdapter(
    var movieList: List<Movie> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<MovieViewHolder>() {

    fun updateList(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(viewholder: MovieViewHolder, position: Int) {
        viewholder.bind(movieList[position], onItemSelected)
    }

    override fun getItemCount() = movieList.size

}