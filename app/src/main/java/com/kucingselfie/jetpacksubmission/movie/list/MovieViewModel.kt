package com.kucingselfie.jetpacksubmission.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
class MovieViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    val movies : LiveData<Result<List<Movie>>> = repository.getMovies()
}
