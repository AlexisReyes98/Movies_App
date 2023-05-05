package com.example.movies_app.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val movieId: Int,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("vote_average") val vote_average: Double,

    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("overview") val overview: String,
)