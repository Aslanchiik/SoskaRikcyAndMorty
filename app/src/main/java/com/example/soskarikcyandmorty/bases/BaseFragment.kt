package com.example.soskarikcyandmorty.bases

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    protected abstract val binding: VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        setupListener()
        setupRequest()
        setupObserves()

    }

    protected open fun initialize() {}

    protected open fun setupViews() {}

    protected open fun setupListener() {}

    protected open fun setupRequest() {}

    protected open fun setupObserves() {}

}