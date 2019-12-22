package com.kucingselfie.jetpacksubmission.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kucingselfie.jetpacksubmission.model.Movie
import com.kucingselfie.jetpacksubmission.model.TVShow
import com.kucingselfie.jetpacksubmission.ui.movie.list.MovieAdapter
import com.kucingselfie.jetpacksubmission.ui.tvshow.list.TVShowAdapter

@BindingAdapter(    "listMovie")
fun listMovie(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("listTvShow")
fun listTvShow(recyclerView: RecyclerView, data: List<TVShow>?) {
    val adapter = recyclerView.adapter as TVShowAdapter
    adapter.submitList(data)
}

@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: Int) {
    Glide.with(imgView.context)
        .load(imgUrl)
        .into(imgView)
}
