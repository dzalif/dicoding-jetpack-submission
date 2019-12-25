package com.kucingselfie.jetpacksubmission.movie.list

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
import com.kucingselfie.jetpacksubmission.databinding.MovieFragmentBinding
import com.kucingselfie.jetpacksubmission.di.Injectable
import com.kucingselfie.jetpacksubmission.testing.OpenForTesting
import com.kucingselfie.jetpacksubmission.ui.home.HomeFragmentDirections
import com.kucingselfie.jetpacksubmission.util.autoCleared
import javax.inject.Inject

@OpenForTesting
class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<MovieFragmentBinding>()
    private var adapter by autoCleared<MovieAdapter>()
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.movie_fragment, container, false, dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val rvAdapter = MovieAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            navController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id))
        }
        binding.results = viewModel.movies
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.data)
            }
        })

        binding.rvMovie.adapter = rvAdapter
        adapter = rvAdapter
    }

    /**
     * Created to be able to override in tests
     */
    open fun navController() = findNavController()

}
