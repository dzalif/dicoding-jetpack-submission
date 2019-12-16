package com.kucingselfie.dicodingjetpacksubmission1.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kucingselfie.dicodingjetpacksubmission1.databinding.DetailMovieFragmentBinding
import com.kucingselfie.dicodingjetpacksubmission1.ui.movie.DetailFragmentArgs

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailMovieFragmentBinding.inflate(inflater)
        val model = DetailFragmentArgs.fromBundle(
            arguments!!
        ).model
        binding.model = model
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

}
