package com.kucingselfie.jetpacksubmission.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kucingselfie.jetpacksubmission.AppExecutors
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.databinding.ItemMovieBinding
import com.kucingselfie.jetpacksubmission.databinding.ItemTvshowBinding
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.ui.common.DataBoundListAdapter

class TVShowAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((TVShow) -> Unit)?
) : DataBoundListAdapter<TVShow, ItemTvshowBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<TVShow>() {
        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemTvshowBinding {
        val binding = DataBindingUtil.inflate<ItemTvshowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_tvshow,
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

    override fun bind(binding: ItemTvshowBinding, item: TVShow) {
        binding.model = item
    }
}