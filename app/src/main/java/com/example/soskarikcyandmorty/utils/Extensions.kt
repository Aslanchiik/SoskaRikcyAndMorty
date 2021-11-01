package com.example.soskarikcyandmorty.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.soskarikcyandmorty.R

//fun RecyclerView.nextScroll(linearLayoutManager: LinearLayoutManager) {
//    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            if (dy > 0) {
//                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
//                val visibleItemCount = linearLayoutManager.childCount
//                val totalItemCount = linearLayoutManager.itemCount
//                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
//
//                }
//            }
//        }
//    })
//}

fun loadImagesWithGlide(imageView: ImageView, url: String, loader: ProgressBar) {
    Glide.with(imageView)
        .load(url)
        .centerCrop()
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean {
                loader.visibility = View.VISIBLE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                loader.visibility = View.VISIBLE
                return false
            }
        })
        .into(imageView)
}