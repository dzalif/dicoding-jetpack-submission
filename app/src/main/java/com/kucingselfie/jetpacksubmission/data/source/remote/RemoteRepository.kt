package com.kucingselfie.jetpacksubmission.data.source.remote

import android.os.Handler
import com.kucingselfie.jetpacksubmission.api.ApiService
import com.kucingselfie.jetpacksubmission.api.MovieResponse
import com.kucingselfie.jetpacksubmission.common.Constant.API_KEY
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class RemoteRepository @Inject constructor(val apiService: ApiService) {
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            apiService.getMovies(API_KEY).enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Timber.e(t.message)
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

    interface LoadMoviesCallback {
        fun onSuccess(response: List<Movie>)
        fun onError()
    }

}