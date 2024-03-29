package com.example.soskarikcyandmorty.presentation.extensions

import android.view.View
import android.widget.SearchView
import com.example.soskarikcyandmorty.utils.OnSingleClickListener
import com.example.soskarikcyandmorty.utils.OnSingleLongClickListener

fun SearchView.searchItem(method: (name: String?) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            method(p0)
            return false
        }

    })
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}

fun View.setOnSingleLongClickListener(v: (View) -> Boolean) {
    setOnLongClickListener(OnSingleLongClickListener(v))
}