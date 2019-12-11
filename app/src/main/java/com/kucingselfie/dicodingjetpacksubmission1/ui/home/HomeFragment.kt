package com.kucingselfie.dicodingjetpacksubmission1.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.kucingselfie.dicodingjetpacksubmission1.R
import com.kucingselfie.dicodingjetpacksubmission1.databinding.HomeFragmentBinding
import com.kucingselfie.dicodingjetpacksubmission1.databinding.MovieFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        binding.fragment = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    fun openMoviesFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment()
        findNavController().navigate(action)
    }

}
