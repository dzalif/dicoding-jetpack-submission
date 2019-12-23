package com.kucingselfie.jetpacksubmission.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.DetailModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailMovieViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> get() = _movieId

    val results: LiveData<Result<DetailModel>> = Transformations.switchMap(_movieId) {
        repository.getDetail(it)
    }

    fun setMovieId(id: Int) {
        _movieId.value = id
    }
}
