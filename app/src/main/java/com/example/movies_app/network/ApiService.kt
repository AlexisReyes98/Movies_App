package com.example.movies_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    lateinit var api: ApiMovies

    val URL = "https://api.themoviedb.org/3/"
    var URL_IMAGES = "https://image.tmdb.org/t/p/w500/"
    val api_key = "04ce1c050a3b24b557772425ac9b31ae"
    val language = "en-US"

    init {
        getRetrofit()
    }

    private fun getRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(ApiMovies::class.java)
    }
}