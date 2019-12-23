package com.kucingselfie.jetpacksubmission.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.data.FakeRemoteData
import com.kucingselfie.jetpacksubmission.data.source.MovieRepository
import com.kucingselfie.jetpacksubmission.model.DetailTvShowModel
import com.kucingselfie.jetpacksubmission.tvshow.detail.DetailTvShowViewModel
import com.kucingselfie.jetpacksubmission.util.mock
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class DetailTvShowViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: DetailTvShowViewModel? = null
    private val repo = Mockito.mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(repo)
    }

    @Test
    fun dontFetchWithoutObservers() {
        viewModel?.setTvShowId(1)
        Mockito.verify(repo, Mockito.never()).getDetail(Mockito.anyInt())
    }

    @Test
    fun fetchWhenObserved() {
        viewModel?.setTvShowId(1)
        viewModel?.results?.observeForever(mock())
        Mockito.verify(repo).getDetailTvShow(1)
    }

    @Test
    fun changeWhileObserved() {
        viewModel?.results?.observeForever(mock())
        viewModel?.setTvShowId(1)
        viewModel?.setTvShowId(2)

        Mockito.verify(repo).getDetailTvShow(1)
        Mockito.verify(repo).getDetailTvShow(2)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getDetailTvShow() {
        val tvShowResult = MutableLiveData<Result<DetailTvShowModel>>()
        val tvShow = FakeRemoteData.getTvshowDetail()
        viewModel?.setTvShowId(tvShow.id)
        tvShowResult.postValue(Result.Success(tvShow))
        Mockito.`when`(repo.getDetailTvShow(tvShow.id)).thenReturn(tvShowResult)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.results?.observeForever(observer as Observer<in Result<DetailTvShowModel>>)
        Mockito.verify(repo).getDetailTvShow(tvShow.id)
        val resultData = viewModel?.results?.value?.data
        assertEquals(tvShow.id, resultData?.id)
        assertEquals(tvShow.description, resultData?.description)
    }
}