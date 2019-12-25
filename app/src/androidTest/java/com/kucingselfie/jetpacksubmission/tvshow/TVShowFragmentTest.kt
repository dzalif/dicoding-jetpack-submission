package com.kucingselfie.jetpacksubmission.tvshow

import androidx.databinding.DataBindingComponent
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.binding.FragmentBindingAdapters
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.testing.SingleFragmentActivity
import com.kucingselfie.jetpacksubmission.tvshow.list.TVShowFragment
import com.kucingselfie.jetpacksubmission.tvshow.list.TvShowViewModel
import com.kucingselfie.jetpacksubmission.util.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class TVShowFragmentTest {
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

    private lateinit var viewModel: TvShowViewModel
    private val result = MutableLiveData<Result<List<TVShow>>>()
    private lateinit var mockBindingAdapter: FragmentBindingAdapters
    private val tvShowFragment = TestTVShowFragment()

    @Before
    fun setUp() {
        viewModel = mock(TvShowViewModel::class.java)
        Mockito.`when`(viewModel.tvShows).thenReturn(result)
        mockBindingAdapter = mock(FragmentBindingAdapters::class.java)
        tvShowFragment.appExecutors = countingAppExecutors.appExecutors
        tvShowFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        tvShowFragment.dataBindingComponent = object : DataBindingComponent {
            override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
                return mockBindingAdapter
            }
        }
        activityRule.activity.setFragment(tvShowFragment)
        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun checkIsDisplayed() {
        val tvShows = mutableListOf<TVShow>()
        tvShows.add(
            TVShow(
                0,
                "foo", "bar", "buzz"
            )
        )
        result.postValue(Result.Success(tvShows))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(listMatcher().atPosition(0))
            .check(matches(hasDescendant(withText("foo"))))

        onView(withId(R.id.rvTvShow))
            .check(matches(isDisplayed()))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.rvTvShow)
    }

    class TestTVShowFragment : TVShowFragment() {
        val navController: NavController = mock(NavController::class.java)
        override fun navController() = navController
    }
}