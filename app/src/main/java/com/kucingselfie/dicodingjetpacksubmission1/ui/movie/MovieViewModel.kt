package com.kucingselfie.dicodingjetpacksubmission1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.dicodingjetpacksubmission1.model.Movie
import com.kucingselfie.dicodingjetpacksubmission1.util.DataDummy.generateMovies

class MovieViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        _movies.postValue(generateMovies())
    }
}
