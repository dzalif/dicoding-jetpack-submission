package com.kucingselfie.jetpacksubmission.tvshow

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.model.DetailModel
import com.kucingselfie.jetpacksubmission.testing.SingleFragmentActivity
import com.kucingselfie.jetpacksubmission.tvshow.list.TVShowFragment
import com.kucingselfie.jetpacksubmission.util.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class TVShowFragmentTest {
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(
        SingleFragmentActivity::class.java)
    private val tvShowFragment = TVShowFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvShowFragment)
    }

    @Test
    fun checkIsDisplayed() {
        onView(ViewMatchers.withId(R.id.rvTvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.rvTvShow)).check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun toDetailTvShow() {
        val mock = Mockito.mock(NavController::class.java)
        val scenario = launchFragmentInContainer<TVShowFragment>()

        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mock)
        }

        onView(ViewMatchers.withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))

        verify(mock).navigate(
            TVShowFragmentDirections.actionTVShowFragmentToDetailMovieFragment(
                DetailModel(
                    0,
                    "Arrow",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    R.drawable.poster_arrow
                )
            ))
    }
}