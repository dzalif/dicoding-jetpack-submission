package com.kucingselfie.jetpacksubmission.api.response

import com.google.gson.annotations.SerializedName
import com.kucingselfie.jetpacksubmission.model.TVShow

data class TVShowResponse(
    val page: Int,
    @SerializedName("total_results")
    val totaResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: MutableList<TVShow>
)