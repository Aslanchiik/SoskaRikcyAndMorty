package com.example.soskarikcyandmorty.bases

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFlowFragment(
    @LayoutRes layoutID: Int,
    @IdRes
    private val navHostFragment: Int
) : Fragment(layoutID) {

    protected open lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
    }

    protected open fun setupNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}
