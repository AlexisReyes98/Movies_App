package com.example.movies_app.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results") val resultsMovies: List<Movie>
)