package com.kucingselfie.jetpacksubmission.movie

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.movie.list.MovieFragment
import com.kucingselfie.jetpacksubmission.testing.SingleFragmentActivity
import com.kucingselfie.jetpacksubmission.ui.home.HomeFragmentDirections
import com.kucingselfie.jetpacksubmission.util.EspressoIdlingResourceJava
import com.kucingselfie.jetpacksubmission.util.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MovieFragmentTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java, true, true)
    private val movieFragment = TestMovieFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResourceJava.getEspressoIdlingResource())
        activityRule.activity.setFragment(movieFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResourceJava.getEspressoIdlingResource())
    }

    @Test
    fun checkIsDisplayed() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun toDetailMovie() {
        val mock = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<MovieFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mock)
        }
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        verify(mock).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            1
        ))
    }

    class TestMovieFragment : MovieFragment() {
        val navController = mock(NavController::class.java)
        override fun navController(): NavController = navController
    }
}