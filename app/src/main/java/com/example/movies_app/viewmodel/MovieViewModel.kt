package com.example.movies_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.model.Movie
import com.example.movies_app.model.Movies
import com.example.movies_app.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel : ViewModel() {
    val movieListTop = MutableLiveData<Movies>()
    val movieListNowPlaying = MutableLiveData<Movies>()
    val movieListDetail = MutableLiveData<Movie>()
    val isLoading = MutableLiveData<Boolean>()

    fun getMoviesTop() {
        viewModelScope.launch {
            isLoading.postValue(true)
            CoroutineScope(Dispatchers.IO).launch {
                val myResponse: Response<Movies> = ApiService.api.getMoviesTopRated()
                if (myResponse.isSuccessful) {
                    Log.i("alexis_1", "Ya funciona :)")
                    movieListTop.postValue(myResponse.body())
                } else {
                    Log.i("alexis_1", "No funciona :(")
                }
            }
            isLoading.postValue(false)
        }
    }

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            isLoading.postValue(true)
            CoroutineScope(Dispatchers.IO).launch {
                val myResponse: Response<Movies> = ApiService.api.getMoviesNowPlaying()
                if (myResponse.isSuccessful) {
                    Log.i("alexis_2", "Ya funciona :)")
                    movieListNowPlaying.postValue(myResponse.body())
                } else {
                    Log.i("alexis_2", "No funciona :(")
                }
            }
            isLoading.postValue(false)
        }
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            CoroutineScope(Dispatchers.IO).launch {
                val myResponse: Response<Movie> = ApiService.api.getMovieId(id)
                if (myResponse.isSuccessful) {
                    Log.i("alexis_3", "Ya funciona :)")
                    movieListDetail.postValue(myResponse.body())
                } else {
                    Log.i("alexis_3", "No funciona :(")
                }
            }
            isLoading.postValue(false)
        }
    }

}