package com.kucingselfie.dicodingjetpacksubmission1.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kucingselfie.dicodingjetpacksubmission1.databinding.DetailMovieFragmentBinding

class DetailMovieFragment : Fragment() {

    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailMovieFragmentBinding.inflate(inflater)
        val model = DetailMovieFragmentArgs.fromBundle(arguments!!).model
        binding.model = model
        binding.executePendingBindings()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
    }

}
