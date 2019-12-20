package com.kucingselfie.jetpacksubmission.ui.tvshow

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvshowViewModelTest {
    private lateinit var viewModel: TvshowViewModel

    @Before
    fun setup() {
        viewModel = TvshowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvshows = viewModel.getTvShows()
        assertNotNull(tvshows)
        assertEquals(10, tvshows.size)
    }
}