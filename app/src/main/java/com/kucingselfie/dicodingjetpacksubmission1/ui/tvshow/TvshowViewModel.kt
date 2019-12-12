package com.kucingselfie.dicodingjetpacksubmission1.ui.tvshow

import androidx.lifecycle.ViewModel
import com.kucingselfie.dicodingjetpacksubmission1.model.TVShow
import com.kucingselfie.dicodingjetpacksubmission1.util.DataDummy.generateTvShows

class TvshowViewModel : ViewModel() {
    fun getTvShows() : List<TVShow> {
        return generateTvShows()
    }
}
