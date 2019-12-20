package com.kucingselfie.jetpacksubmission.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.TVShow
import javax.inject.Inject

class TvshowViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    val tvShows : LiveData<Result<List<TVShow>>> = repository.getTvShows()
}
