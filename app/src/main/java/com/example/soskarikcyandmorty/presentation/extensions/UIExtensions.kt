package com.example.soskarikcyandmorty.presentation.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadImagesWithGlide(url: String, loader: ProgressBar) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>,
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
        .into(this)
}

fun ImageView.setImage(url: String) =
    Glide.with(this)
        .load(url)
        .into(this)

fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.bindUIToLoadState(
    recyclerView: RecyclerView,
    progressBar: ProgressBar,
    listener: (CombinedLoadStates) -> Unit
) {
    addLoadStateListener { loadStates ->
        recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        progressBar.isVisible = loadStates.refresh is LoadState.Loading
        listener(loadStates)
    }
}