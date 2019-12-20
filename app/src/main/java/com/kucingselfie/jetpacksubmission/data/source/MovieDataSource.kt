package com.kucingselfie.jetpacksubmission.data.source

import androidx.lifecycle.LiveData
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.model.Movie

interface MovieDataSource {
    fun getMovies(): LiveData<Result<List<Movie>>>
}