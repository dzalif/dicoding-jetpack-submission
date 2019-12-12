package com.kucingselfie.dicodingjetpacksubmission1.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kucingselfie.dicodingjetpacksubmission1.model.TVShow
import com.kucingselfie.dicodingjetpacksubmission1.util.DataDummy

class TvshowViewModel : ViewModel() {
    private val _tvShows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> get() = _tvShows

    init {
        _tvShows.postValue(DataDummy.generateTvShows())
    }
}
