package com.kucingselfie.jetpacksubmission.tvshow.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kucingselfie.jetpacksubmission.AppExecutors
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.jetpacksubmission.databinding.TvshowFragmentBinding
import com.kucingselfie.jetpacksubmission.di.Injectable
import com.kucingselfie.jetpacksubmission.testing.OpenForTesting
import com.kucingselfie.jetpacksubmission.ui.home.HomeFragmentDirections
import com.kucingselfie.jetpacksubmission.util.autoCleared
import javax.inject.Inject

@OpenForTesting
class TVShowFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<TvshowFragmentBinding>()
    private var adapter by autoCleared<TVShowAdapter>()
    private val viewModel: TvShowViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<TvshowFragmentBinding>(
            inflater, R.layout.tvshow_fragment, container, false, dataBindingComponent
        )
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val rvAdapter = TVShowAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            navController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailTvShowFragment(it.id)
            )
        }

        binding.results = viewModel.tvShows
        viewModel.tvShows.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.data)
            }
        })

        binding.rvTvShow.adapter = rvAdapter
        adapter = rvAdapter
    }

    fun navController() = findNavController()
}
