package com.kucingselfie.dicodingjetpacksubmission1.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kucingselfie.dicodingjetpacksubmission1.model.Movie
import com.kucingselfie.dicodingjetpacksubmission1.ui.movie.MovieAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: Int) {
    Glide.with(imgView.context)
        .load(imgUrl)
        .into(imgView)
}
