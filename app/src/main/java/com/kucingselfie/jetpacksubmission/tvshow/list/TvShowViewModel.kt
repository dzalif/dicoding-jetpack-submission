package com.kucingselfie.jetpacksubmission.tvshow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
class TvShowViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    val tvShows : LiveData<Result<List<TVShow>>> = repository.getTvShows()
}
