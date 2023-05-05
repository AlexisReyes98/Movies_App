package com.example.movies_app.network

import com.example.movies_app.model.Movie
import com.example.movies_app.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMovies {
    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language
    ): Response<Movies>

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language
    ): Response<Movies>

    @GET("movie/{movie_id}")
    suspend fun getMovieId(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language
    ): Response<Movie>
}