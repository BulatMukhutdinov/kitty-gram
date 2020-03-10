package tat.mukhutdinov.kittygram.infrastructure.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String?) {
    Glide.withSafe(imageView)
        ?.load(url)
        ?.into(imageView)

    imageView.clipToOutline = true
}