package com.kucingselfie.jetpacksubmission.api

import com.kucingselfie.jetpacksubmission.api.response.MovieResponse
import com.kucingselfie.jetpacksubmission.api.response.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") type: String
    ): Call<MovieResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") type: String
    ): Call<TVShowResponse>
}