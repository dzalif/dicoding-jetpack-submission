package com.kucingselfie.jetpacksubmission.model

import com.google.gson.annotations.SerializedName

data class DetailModel(
    val id: Int,
    val title: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("poster_path")
    val image: String
)

data class DetailTvShowModel(
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("poster_path")
    val image: String
)