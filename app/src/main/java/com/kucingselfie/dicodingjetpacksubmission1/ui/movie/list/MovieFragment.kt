package com.kucingselfie.dicodingjetpacksubmission1.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.dicodingjetpacksubmission1.AppExecutors
import com.kucingselfie.dicodingjetpacksubmission1.R
import com.kucingselfie.dicodingjetpacksubmission1.binding.FragmentDataBindingComponent
import com.kucingselfie.dicodingjetpacksubmission1.databinding.MovieFragmentBinding
import com.kucingselfie.dicodingjetpacksubmission1.di.Injectable
import com.kucingselfie.dicodingjetpacksubmission1.model.DetailModel
import com.kucingselfie.dicodingjetpacksubmission1.ui.movie.MovieFragmentDirections
import com.kucingselfie.dicodingjetpacksubmission1.util.autoCleared
import javax.inject.Inject

class MovieFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    val viewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    // mutable for testing
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<MovieFragmentBinding>()

    var adapter by autoCleared<MovieAdapter>()

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
            val model = DetailModel(
                it.id,
                it.title,
                it.description,
                it.image
            )
            navController().navigate(
                MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(model))
        }
        binding.rvMovie.adapter = adapter
        adapter = rvAdapter
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()

}
