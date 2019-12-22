package com.kucingselfie.jetpacksubmission.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.databinding.HomeFragmentBinding
import com.kucingselfie.jetpacksubmission.ui.movie.list.MovieFragment
import com.kucingselfie.jetpacksubmission.ui.tvshow.list.TVShowFragment

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = HomeFragmentBinding.inflate(inflater)
        binding.executePendingBindings()
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPagerAdapter = ViewPagerFragment(childFragmentManager, requireContext())
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)
        return binding.root
    }

    class ViewPagerFragment(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm) {
        private val tabNextMatch = context.resources.getString(R.string.movie)
        private val tabLastMatch = context.resources.getString(R.string.tv_show)

        private val tabTitles = arrayOf(tabNextMatch, tabLastMatch)

        private val pages: List<Fragment> = listOf(
            MovieFragment(),
            TVShowFragment()
        )

        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {

            return pages.size
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }

}
