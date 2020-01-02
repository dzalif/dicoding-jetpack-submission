package com.kucingselfie.jetpacksubmission.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.FakeRemoteData
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.tvshow.list.TvShowViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@Suppress("UNCHECKED_CAST")
@RunWith(JUnit4::class)
class TvShowViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvShowViewModel? = null
    private val repo = Mockito.mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repo)
    }

    @Test
    fun getTvShows() {
        val tvShowsResult = MutableLiveData<Result<List<TVShow>>>()
        val tvShows = FakeRemoteData.getDummyTVShows()
        tvShowsResult.postValue(Result.Success(tvShows))
        `when`(repo.getTvShows()).thenReturn(tvShowsResult)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.tvShows?.observeForever(observer as Observer<in Result<List<TVShow>>>)
        verify(repo).getTvShows()
    }
}