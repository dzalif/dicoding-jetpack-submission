package com.kucingselfie.dicodingjetpacksubmission1.ui.movie

import com.kucingselfie.dicodingjetpacksubmission1.ui.movie.list.MovieViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}