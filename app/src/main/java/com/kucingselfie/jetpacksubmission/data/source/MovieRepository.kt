package com.kucingselfie.jetpacksubmission.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.remote.RemoteRepository
import com.kucingselfie.jetpacksubmission.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteRepository: RemoteRepository
) : MovieDataSource {
    override fun getMovies(): LiveData<Result<List<Movie>>> {
        val movies = MutableLiveData<Result<List<Movie>>>()
        movies.postValue(Result.Loading(null))
        remoteRepository.getMovies(object : RemoteRepository.LoadMoviesCallback {
            override fun onSuccess(response: List<Movie>) {
                movies.postValue(Result.Success(response))
            }

            override fun onError() {
                movies.postValue(Result.Error("Error message", null))
            }
        })
        return movies
    }
}

