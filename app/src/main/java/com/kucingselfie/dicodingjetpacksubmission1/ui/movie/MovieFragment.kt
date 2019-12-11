package com.kucingselfie.dicodingjetpacksubmission1.ui.movie

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.kucingselfie.dicodingjetpacksubmission1.R
import com.kucingselfie.dicodingjetpacksubmission1.databinding.MovieFragmentBinding

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: MovieFragmentBinding

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
        binding.rvMovie.adapter = MovieAdapter()
    }

}
