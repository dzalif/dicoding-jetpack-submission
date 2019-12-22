package com.kucingselfie.jetpacksubmission.api

import com.kucingselfie.jetpacksubmission.api.response.MovieResponse
import com.kucingselfie.jetpacksubmission.api.response.TVShowResponse
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.model.DetailTvShowModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("tv/{tv_id}")
    fun getTvDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") type: String
    ) : Call<DetailTvShowModel>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") type: String
    ) : Call<DetailModel>
}