package com.kucingselfie.dicodingjetpacksubmission1.ui.movie

import androidx.lifecycle.ViewModel
import com.kucingselfie.dicodingjetpacksubmission1.model.Movie
import com.kucingselfie.dicodingjetpacksubmission1.util.DataDummy.generateMovies

class MovieViewModel : ViewModel() {
    fun getMovies() : List<Movie> {
        return generateMovies()
    }
}
