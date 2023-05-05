package com.example.movies_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.movies_app.databinding.ActivityMovieDetailBinding
import com.example.movies_app.network.ApiService
import com.example.movies_app.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: Int = intent.getStringExtra(EXTRA_ID).orEmpty().toInt()
        getMovieInformation(id)
    }

    private fun getMovieInformation(id: Int) {
        viewModel.getMovieDetail(id)

        viewModel.movieListDetail.observe(this, Observer {
            val urlImage = ApiService.URL_IMAGES + it.backdrop_path
            Picasso.get().load(urlImage).into(binding.ivMovie)
            binding.tvMovieTitle.text = it.title
            binding.tvMovieOverview.text = it.overview
            updateHeight(binding.viewScore, it.vote_average)
        })
    }

    private fun updateHeight(view: View, stat: Double) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }
}