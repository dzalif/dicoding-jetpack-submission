package com.kucingselfie.jetpacksubmission.ui.tvshow

import androidx.lifecycle.ViewModel
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.util.DataDummy.generateTvShows

class TvshowViewModel : ViewModel() {
    fun getTvShows() : List<TVShow> {
        return generateTvShows()
    }
}
