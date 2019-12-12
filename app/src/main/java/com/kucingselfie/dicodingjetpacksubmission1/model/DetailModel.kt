package com.kucingselfie.dicodingjetpacksubmission1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    val id: Int,
    val title: String,
    val description: String,
    val image: Int
) : Parcelable