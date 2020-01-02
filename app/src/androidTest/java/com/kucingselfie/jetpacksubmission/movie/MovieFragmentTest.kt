package com.kucingselfie.jetpacksubmission.movie

import androidx.databinding.DataBindingComponent
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.binding.FragmentBindingAdapters
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.movie.list.MovieFragment
import com.kucingselfie.jetpacksubmission.movie.list.MovieViewModel
import com.kucingselfie.jetpacksubmission.testing.SingleFragmentActivity
import com.kucingselfie.jetpacksubmission.ui.home.HomeFragmentDirections
import com.kucingselfie.jetpacksubmission.util.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MovieFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(
        SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    private lateinit var viewModel: MovieViewModel
    private val result = MutableLiveData<Result<List<Movie>>>()
    private lateinit var mockBindingAdapter: FragmentBindingAdapters
    private val movieFragment = TestMovieFragment()

    @Before
    fun setUp() {
        viewModel = mock(MovieViewModel::class.java)
        Mockito.`when`(viewModel.movies).thenReturn(result)
        mockBindingAdapter = mock(FragmentBindingAdapters::class.java)
        movieFragment.appExecutors = countingAppExecutors.appExecutors
        movieFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        movieFragment.dataBindingComponent = object : DataBindingComponent {
            override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
                return mockBindingAdapter
            }
        }
        activityRule.activity.setFragment(movieFragment)
        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun checkIsDisplayed() {
        val movies = mutableListOf<Movie>()
        movies.add(
            Movie(
                0,
                "foo", "bar", "buzz"
            )
        )
        result.postValue(Result.Success(movies))
        //Check progress bar not displayed
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        onView(listMatcher().atPosition(0))
            .check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("foo"))))

        onView(withId(R.id.rvMovie))
            .check(matches(isDisplayed()))
    }

    @Test
    fun toDetailMovie() {
        val movies = mutableListOf<Movie>()
        movies.add(
            Movie(
                1,
                "foo", "bar", "buzz"
            )
        )
        result.postValue(Result.Success(movies))
        onView(ViewMatchers.withText("foo")).perform(click())
        verify(movieFragment.navController).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(1))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.rvMovie)
    }

    class TestMovieFragment : MovieFragment() {
        val navController = mock<NavController>()
        override fun navController(): NavController = navController
    }
}