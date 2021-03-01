package com.paras.yotubepopularvideo.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.paras.yotubepopularvideo.R

class BindingAdapter {
    companion object {
       private const val IMAGE_BASE_URL = "https://img.youtube.com/vi/"

        @JvmStatic
        @BindingAdapter("glideImageSrc")
        fun imageBinder(imageView: ImageView, videoId: String) {
            val imageUrl = "${IMAGE_BASE_URL}${videoId}/0.jpg"
            Glide.with(imageView.context)
                    .load(imageUrl)
                    .error(R.drawable.not_found)
                    .into(imageView)
        }
    }
}