package com.example.movies_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies_app.adapters.MovieAdapter
import com.example.movies_app.databinding.ActivityMovieBinding
import com.example.movies_app.viewmodel.MovieViewModel
import com.example.movies_app.view.MovieDetailActivity.Companion.EXTRA_ID

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapterMovieTop: MovieAdapter
    private lateinit var adapterMovieNowPlaying: MovieAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }
            override fun onQueryTextChange(newText: String?) = false
        })

        searchByTopMovies()
        adapterMovieTop = MovieAdapter { movieId -> navigateToDetail(movieId) }
        binding.rvMovieTop.setHasFixedSize(true)
        binding.rvMovieTop.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieTop.adapter = adapterMovieTop

        searchByNowPlayingMovies()
        adapterMovieNowPlaying = MovieAdapter { movieId -> navigateToDetail(movieId) }
        binding.rvMovieNowPlaying.setHasFixedSize(true)
        binding.rvMovieNowPlaying.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieNowPlaying.adapter = adapterMovieNowPlaying

        Toast.makeText(
            applicationContext,
            "Conexión exitosa!",
            Toast.LENGTH_SHORT
        ).show();
    }

    private fun searchByTopMovies() {
        viewModel.getMoviesTop()

        viewModel.movieListTop.observe(this, Observer {
            adapterMovieTop.updateList(it.resultsMovies)
        })
        viewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }

    private fun searchByNowPlayingMovies() {
        viewModel.getMoviesNowPlaying()

        viewModel.movieListNowPlaying.observe(this, Observer {
            adapterMovieNowPlaying.updateList(it.resultsMovies)
        })
        viewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }

    private fun searchByName(query: String) {
        // Search name
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}