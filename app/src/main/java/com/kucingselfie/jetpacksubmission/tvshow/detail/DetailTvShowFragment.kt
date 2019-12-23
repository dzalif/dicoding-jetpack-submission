package com.kucingselfie.jetpacksubmission.tvshow.detail

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
import com.kucingselfie.jetpacksubmission.AppExecutors
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.jetpacksubmission.common.Result
import com.kucingselfie.jetpacksubmission.databinding.DetailTvShowFragmentBinding
import com.kucingselfie.jetpacksubmission.di.Injectable
import com.kucingselfie.jetpacksubmission.util.autoCleared
import com.kucingselfie.jetpacksubmission.util.gone
import com.kucingselfie.jetpacksubmission.util.visible
import javax.inject.Inject

class DetailTvShowFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<DetailTvShowFragmentBinding>()

    private val viewModel: DetailTvShowViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_tv_show_fragment, container, false, dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val tvShowId = arguments?.let { DetailTvShowFragmentArgs.fromBundle(it).tvId }
        tvShowId?.let { viewModel.setTvShowId(it) }
        binding.tvShowId = viewModel.tvShowId
        binding.results = viewModel.results
        viewModel.results.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {
                        binding.group.gone()
                    }
                    is Result.Success -> {
                        binding.group.visible()
                        binding.model = it.data
                    }
                }
            }
        })
    }

}
