package com.kucingselfie.dicodingjetpacksubmission1.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.dicodingjetpacksubmission1.databinding.MovieFragmentBinding
import com.kucingselfie.dicodingjetpacksubmission1.model.DetailModel

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: MovieFragmentBinding

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        adapter = MovieAdapter {
            val model = DetailModel(
                it.id,
                it.title,
                it.description,
                it.image
            )
            val action = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(model)
            findNavController().navigate(action)
        }
        binding.rvMovie.adapter = adapter
    }

}
