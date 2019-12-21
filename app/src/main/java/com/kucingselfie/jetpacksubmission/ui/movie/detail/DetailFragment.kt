package com.kucingselfie.jetpacksubmission.ui.movie.detail

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
import com.kucingselfie.jetpacksubmission.databinding.DetailFragmentBinding
import com.kucingselfie.jetpacksubmission.di.Injectable
import com.kucingselfie.jetpacksubmission.util.autoCleared
import com.kucingselfie.jetpacksubmission.util.gone
import com.kucingselfie.jetpacksubmission.util.visible
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<DetailFragmentBinding>()

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_fragment, container, false, dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val movieId = arguments?.let { DetailFragmentArgs.fromBundle(it).dataId }
        movieId?.let { viewModel.setMovieId(it) }
        binding.movieId = viewModel.movieId
        binding.results = viewModel.results
        viewModel.results.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {
                        binding.group.gone()
                    }
                    is Result.Success -> {
                        binding.group.visible()
                        binding.result = it
                    }
                }
            }
            binding.model = it.data
        })
    }
}


