package com.kucingselfie.jetpacksubmission.tvshow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.TVShow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    val tvShows : LiveData<Result<List<TVShow>>> = repository.getTvShows()
}
