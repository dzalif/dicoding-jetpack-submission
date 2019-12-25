package com.kucingselfie.jetpacksubmission.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kucingselfie.jetpacksubmission.R
import com.kucingselfie.jetpacksubmission.common.Constant.BASE_URL_IMAGE
import com.kucingselfie.jetpacksubmission.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
    @BindingAdapter("imageUrl")
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            Glide.with(imgView.context)
                .load("$BASE_URL_IMAGE$imgUrl")
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_movie_black))
                .into(imgView)
        }
    }
}