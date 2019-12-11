package com.kucingselfie.dicodingjetpacksubmission1.ui.tvshow

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kucingselfie.dicodingjetpacksubmission1.R

class TVShowFragment : Fragment() {

    companion object {
        fun newInstance() = TVShowFragment()
    }

    private lateinit var viewModel: TvshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tvshow_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TvshowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
