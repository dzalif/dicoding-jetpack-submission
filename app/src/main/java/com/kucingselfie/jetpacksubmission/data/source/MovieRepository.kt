package com.kucingselfie.jetpacksubmission.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.remote.RemoteRepositoryJava
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.model.DetailTvShowModel
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class MovieRepository @Inject constructor(
    private val remoteRepository: RemoteRepositoryJava
) : MovieDataSource {
    override fun getTvShows(): LiveData<Result<List<TVShow>>> {
        val tvShows = MutableLiveData<Result<List<TVShow>>>()
        tvShows.postValue(Result.Loading(null))
        remoteRepository.getTVShows(object : RemoteRepositoryJava.LoadTvShowsCallback {
            override fun onSuccess(response: List<TVShow>) {
                tvShows.postValue(Result.Success(response))
            }
            override fun onError(message: String?) {
                message?.let {
                    tvShows.postValue(Result.Error(message, null))
                }
            }
        })
        return tvShows
    }

    override fun getMovies(): LiveData<Result<List<Movie>>> {
        val movies = MutableLiveData<Result<List<Movie>>>()
        movies.postValue(Result.Loading(null))
        remoteRepository.getMovies(object : RemoteRepositoryJava.LoadMoviesCallback {
            override fun onSuccess(response: List<Movie>) {
                movies.postValue(Result.Success(response))
            }
            override fun onError(message: String?) {
                message?.let {
                    movies.postValue(Result.Error(message, null))
                }
            }
        })
        return movies
    }

    override fun getDetail(id: Int): LiveData<Result<DetailModel>> {
        val details = MutableLiveData<Result<DetailModel>>()
        details.postValue(Result.Loading(null))
        remoteRepository.getMovieDetail(id, object : RemoteRepositoryJava.LoadDetailCallback {
            override fun onSuccess(response: DetailModel) {
                details.postValue(Result.Success(response))
            }

            override fun onError(message: String?) {
                message?.let {
                    details.postValue(Result.Error(message, null))
                }
            }

        })
        return details
    }

    override fun getDetailTvShow(id: Int): LiveData<Result<DetailTvShowModel>> {
        val details = MutableLiveData<Result<DetailTvShowModel>>()
        details.postValue(Result.Loading(null))
        remoteRepository.getTVShowDetail(id, object : RemoteRepositoryJava.LoadDetailTvShowCallback {
            override fun onSuccess(response: DetailTvShowModel) {
                details.postValue(Result.Success(response))
            }

            override fun onError(message: String?) {
                message?.let {
                    details.postValue(Result.Error(message, null))
                }
            }
        })
        return details
    }
}

