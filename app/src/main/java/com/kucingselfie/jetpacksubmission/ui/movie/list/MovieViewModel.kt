package com.kucingselfie.jetpacksubmission.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    val movies : LiveData<Result<List<Movie>>> = repository.getMovies()
}
