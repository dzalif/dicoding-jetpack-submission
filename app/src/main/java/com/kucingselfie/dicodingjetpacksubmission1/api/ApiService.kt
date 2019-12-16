package com.kucingselfie.dicodingjetpacksubmission1.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular")
    fun getMovies(@Path("login") login: String): LiveData<ApiResponse<List<MovieResponse>>>
}