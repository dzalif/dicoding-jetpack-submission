package com.kucingselfie.jetpacksubmission.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.FakeRemoteData
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.movie.detail.DetailMovieViewModel
import com.kucingselfie.jetpacksubmission.util.mock
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
@RunWith(JUnit4::class)
class DetailMovieViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: DetailMovieViewModel? = null
    private val repo = mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repo)
    }

    @Test
    fun dontFetchWithoutObservers() {
        viewModel?.setMovieId(1)
        verify(repo, never()).getDetail(anyInt())
    }

    @Test
    fun fetchWhenObserved() {
        viewModel?.setMovieId(1)
        viewModel?.results?.observeForever(mock())
        verify(repo).getDetail(1)
    }

    @Test
    fun changeWhileObserved() {
        viewModel?.results?.observeForever(mock())
        viewModel?.setMovieId(1)
        viewModel?.setMovieId(2)

        verify(repo).getDetail(1)
        verify(repo).getDetail(2)
    }

    @Test
    fun getDetailMovie() {
        val movieResult = MutableLiveData<Result<DetailModel>>()
        val movie = FakeRemoteData.getMovieDetail()
        viewModel?.setMovieId(movie.id)
        movieResult.postValue(Result.Success(movie))
        `when`(repo.getDetail(movie.id)).thenReturn(movieResult)
        val observer = mock(Observer::class.java)
        viewModel?.results?.observeForever(observer as Observer<in Result<DetailModel>>)
        verify(repo).getDetail(movie.id)
        val resultData = viewModel?.results?.value?.data
        assertEquals(movie.id, resultData?.id)
        assertEquals(movie.description, resultData?.description)
    }
}