package com.kucingselfie.jetpacksubmission.ui.movie

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.testing.SingleFragmentActivity
import com.kucingselfie.jetpacksubmission.ui.movie.list.MovieFragment
import com.kucingselfie.jetpacksubmission.util.RecyclerViewItemCountAssertion
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
    var activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)
    private val movieFragment =
        MovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun checkIsDisplayed() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun toDetailMovie() {
        val mock = mock(NavController::class.java)
        val scenario = launchFragmentInContainer<MovieFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mock)
        }
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        verify(mock).navigate(MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(
            DetailModel(
                0,
                "Alita: Battle Angle",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                R.drawable.poster_alita
            )
        ))
    }
}