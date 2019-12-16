package com.kucingselfie.dicodingjetpacksubmission1.api

import com.google.gson.annotations.SerializedName
import com.kucingselfie.dicodingjetpacksubmission1.model.Movie

data class MovieResponse(
    val page: Int,
    @SerializedName("total_results")
    val totaResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: MutableList<Movie>
)