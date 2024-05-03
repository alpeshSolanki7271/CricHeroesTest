package com.cricheroestest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.cricheroestest.R

@BindingAdapter("imgUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_cric)
        error(R.drawable.ic_cric)
    }
}