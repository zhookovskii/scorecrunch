package com.zhukovskii.scorecrunch.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.zhukovskii.scorecrunch.R


object ImageLoader {

    fun load(
        context: Context,
        url: String?,
        view: ImageView
    ) {
        if (url?.endsWith(".svg") == true) {
            GlideToVectorYou.init()
                .with(context)
                .load(Uri.parse(url), view)
        } else {
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.trophy)
                .into(view)
        }
    }
}