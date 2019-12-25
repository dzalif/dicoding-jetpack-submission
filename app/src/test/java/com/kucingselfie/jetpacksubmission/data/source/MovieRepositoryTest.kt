package com.kucingselfie.jetpacksubmission.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kucingselfie.jetpacksubmission.data.FakeRemoteData
import com.kucingselfie.jetpacksubmission.data.source.remote.RemoteRepositoryJava
import com.kucingselfie.jetpacksubmission.util.LiveDataTestUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MovieRepositoryTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepositoryJava::class.java)
    private val fakeRepository = FakeMovieRepository(remote)

    private val movies = FakeRemoteData.getDummyMovies()
    private val tvShows = FakeRemoteData.getDummyTVShows()
    private val movieId = movies[0].id
    private val tvId = tvShows[0].id
    private val movieDetailResponse = FakeRemoteData.getMovieDetail()
    private val tvShowDetailResponse = FakeRemoteData.getTvshowDetail()

    @Test
    fun getMovies() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepositoryJava.LoadMoviesCallback
            callback.onSuccess(movies)
            null
        }.`when`(remote).getMovies(any(RemoteRepositoryJava.LoadMoviesCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getMovies())
        verify(remote, times(1)).getMovies(any(RemoteRepositoryJava.LoadMoviesCallback::class.java))

        assertNotNull(result)
        assertEquals(movies.size, result.data?.size)
    }

    @Test
    fun getTVShows() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepositoryJava.LoadTvShowsCallback
            callback.onSuccess(tvShows)
            null
        }.`when`(remote).getTVShows(any(RemoteRepositoryJava.LoadTvShowsCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getTvShows())
        verify(remote, times(1)).getTVShows(any(RemoteRepositoryJava.LoadTvShowsCallback::class.java))

        assertNotNull(result)
        assertEquals(movies.size, result.data?.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer {
            val callback = it.arguments[1] as RemoteRepositoryJava.LoadDetailCallback
            callback.onSuccess(movieDetailResponse)
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any(RemoteRepositoryJava.LoadDetailCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getDetail(movieId))
        verify(remote, times(1)).getMovieDetail(eq(movieId), any(RemoteRepositoryJava.LoadDetailCallback::class.java))

        assertNotNull(result)
        assertEquals(movieId, result.data?.id)
        assertEquals(movies[0].overview, result.data?.description)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer {
            val callback = it.arguments[1] as RemoteRepositoryJava.LoadDetailTvShowCallback
            callback.onSuccess(tvShowDetailResponse)
            null
        }.`when`(remote).getTVShowDetail(eq(tvId), any(RemoteRepositoryJava.LoadDetailTvShowCallback::class.java))

        val result = LiveDataTestUtil.getValue(fakeRepository.getDetailTvShow(tvId))
        verify(remote, times(1)).getTVShowDetail(eq(tvId), any(RemoteRepositoryJava.LoadDetailTvShowCallback::class.java))

        assertNotNull(result)
        assertEquals(tvId, result.data?.id)
        assertEquals(tvShows[0].overview, result.data?.description)
    }
}