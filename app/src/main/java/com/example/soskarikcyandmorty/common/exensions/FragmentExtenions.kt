package com.example.soskarikcyandmorty.common.exensions

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.soskarikcyandmorty.R

fun Fragment.setDropMenu(): ArrayAdapter<String> = object : ArrayAdapter<String>(
    requireActivity(),
    R.layout.item_drop_down,
    resources.getStringArray(R.array.status)
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        parent.overScrollMode = View.OVER_SCROLL_NEVER
        return super.getView(position, convertView, parent)
    }
}

fun Fragment.setDropMenuForGender(): ArrayAdapter<String> = object : ArrayAdapter<String>(
    requireActivity(),
    R.layout.item_drop_down,
    resources.getStringArray(R.array.gender)
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        parent.overScrollMode = View.OVER_SCROLL_NEVER
        return super.getView(position, convertView, parent)
    }
}