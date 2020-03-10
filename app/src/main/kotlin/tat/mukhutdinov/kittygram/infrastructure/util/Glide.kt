package tat.mukhutdinov.kittygram.infrastructure.util

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class Glide {

    companion object {

        fun withSafe(view: View): RequestManager? {
            val context = view.context

            if (context is FragmentActivity && context.isDestroyed) {
                return null
            }

            return Glide.with(view.context)
        }
    }
}