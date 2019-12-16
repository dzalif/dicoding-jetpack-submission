package com.kucingselfie.dicodingjetpacksubmission1.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kucingselfie.dicodingjetpacksubmission1.AppExecutors
import com.kucingselfie.dicodingjetpacksubmission1.R
import com.kucingselfie.dicodingjetpacksubmission1.databinding.ItemMovieBinding
import com.kucingselfie.dicodingjetpacksubmission1.model.Movie
import com.kucingselfie.dicodingjetpacksubmission1.ui.common.DataBoundListAdapter

class MovieAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((Movie) -> Unit)?
) : DataBoundListAdapter<Movie, ItemMovieBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemMovieBinding {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.model?.let {
                clickListener?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemMovieBinding, item: Movie) {
        binding.model = item
    }
}