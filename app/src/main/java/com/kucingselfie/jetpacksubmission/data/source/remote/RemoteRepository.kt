package com.kucingselfie.jetpacksubmission.data.source.remote

import android.os.Handler
import com.kucingselfie.jetpacksubmission.api.ApiService
import com.kucingselfie.jetpacksubmission.api.response.MovieResponse
import com.kucingselfie.jetpacksubmission.api.response.TVShowResponse
import com.kucingselfie.jetpacksubmission.common.Constant.API_KEY
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(val apiService: ApiService) {
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            apiService.getMovies(API_KEY).enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    callback.onError(t.message)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body().let {
                            callback.onSuccess(it!!.results)
                        }
                    }
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            apiService.getTvShows(API_KEY).enqueue(object : Callback<TVShowResponse> {
                override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                    callback.onError(t.message)
                }

                override fun onResponse(
                    call: Call<TVShowResponse>,
                    response: Response<TVShowResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccess(it.results)
                        }
                    }
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetail(id: Int, callback: LoadDetailCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            apiService.getMovieDetail(id, API_KEY).enqueue(object : Callback<DetailModel> {
                override fun onFailure(call: Call<DetailModel>, t: Throwable) {
                    callback.onError(t.message)
                }

                override fun onResponse(call: Call<DetailModel>, response: Response<DetailModel>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            callback.onSuccess(it)
                        }
                    }
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTvShowsCallback {
        fun onSuccess(response: List<TVShow>)
        fun onError(message: String?)
    }

    interface LoadMoviesCallback {
        fun onSuccess(response: List<Movie>)
        fun onError(message: String?)
    }

    interface LoadDetailCallback {
        fun onSuccess(response: DetailModel)
        fun onError(message: String?)
    }

}