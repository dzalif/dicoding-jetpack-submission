package com.kucingselfie.jetpacksubmission.data.source

import androidx.lifecycle.LiveData
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.model.TVShow

interface MovieDataSource {
    fun getMovies(): LiveData<Result<List<Movie>>>
    fun getTvShows(): LiveData<Result<List<TVShow>>>
    fun getDetail(it: Int): LiveData<Result<DetailModel>>
}