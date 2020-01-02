package com.kucingselfie.jetpacksubmission.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.FakeRemoteData
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.movie.list.MovieViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@Suppress("UNCHECKED_CAST")
@RunWith(JUnit4::class)
class MovieViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: MovieViewModel? = null
    private val repo = mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repo)
    }

    @Test
    fun getMovies() {
        val moviesResult = MutableLiveData<Result<List<Movie>>>()
        val movies = FakeRemoteData.getDummyMovies()
        moviesResult.postValue(Result.Success(movies))
        Mockito.`when`(repo.getMovies()).thenReturn(moviesResult)
        val observer = mock(Observer::class.java)
        viewModel?.movies?.observeForever(observer as Observer<in Result<List<Movie>>>)
        verify(repo).getMovies()
    }
}