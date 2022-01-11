package com.example.soskarikcyandmorty.utils

import android.view.View

class OnSingleLongClickListener(listener: View.OnLongClickListener) : View.OnLongClickListener {

    private var onClickListener: View.OnLongClickListener = listener

    override fun onLongClick(p0: View?): Boolean {
        val currentTimeMillis = System.currentTimeMillis()

        if (currentTimeMillis >= previousClickTimeMillis + DELAY_MILLIS) {
            previousClickTimeMillis = currentTimeMillis
            onClickListener.onLongClick(p0)
        }
        return false
    }

    companion object {
        private const val DELAY_MILLIS = 200L
        private var previousClickTimeMillis = 0L
    }
}