package tat.mukhutdinov.kittygram.infrastructure.util

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("image")
fun setImage(imageView: ImageView, url: String?) {
    Glide.withSafe(imageView)
        ?.load(url)
        ?.apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
        ?.into(imageView)
}

@BindingAdapter("htmlText")
fun setHtmlText(textView: TextView, text: String?) {
    text?.let {
        textView.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
    }
}