package com.kucingselfie.dicodingjetpacksubmission1.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kucingselfie.dicodingjetpacksubmission1.databinding.TvshowFragmentBinding

class TVShowFragment : Fragment() {

    private lateinit var viewModel: TvshowViewModel
    private lateinit var binding: TvshowFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TvshowFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TvshowViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.rvTvShow.adapter = TVShowAdapter()
    }

}
