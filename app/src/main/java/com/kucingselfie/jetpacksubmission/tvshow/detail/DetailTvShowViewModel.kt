package com.kucingselfie.jetpacksubmission.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.DetailTvShowModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailTvShowViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    private val _tvShowId = MutableLiveData<Int>()
    val tvShowId: LiveData<Int> get() = _tvShowId

    val results: LiveData<Result<DetailTvShowModel>> = Transformations.switchMap(_tvShowId) {
        repository.getDetailTvShow(it)
    }

    fun setTvShowId(id: Int) {
        _tvShowId.value = id
    }
}
