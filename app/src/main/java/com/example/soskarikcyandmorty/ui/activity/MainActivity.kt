package com.example.soskarikcyandmorty.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationListener: OnBottomNavigationSelected
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() = with(binding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.characterFragment,
                R.id.episodeFragment,
                R.id.locationFragment,
                R.id.filterFragment
            )
        )

        setupWithNavController(insideToolbar, navController, appBarConfiguration)
        setupWithNavController(bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.characterDetailFragment ||
                destination.id == R.id.episodeDetailFragment ||
                destination.id == R.id.locationDetailFragment
            ) {
                if (insideToolbar.parent is AppBarLayout) {
                    (insideToolbar.parent as AppBarLayout).setExpanded(true, true)
                }
            }
            when (destination.id) {
                R.id.characterFragment -> {
                    whetherToShow(position = true, position1 = true)
                }
                R.id.locationFragment -> {
                    whetherToShow(position = true, position1 = true)
                }
                R.id.episodeFragment -> {
                    whetherToShow(position = true, position1 = true)
                }
                R.id.noConnectFragment -> {
                    whetherToShow(position = false, position1 = false)
                }
                R.id.characterDetailFragment -> {
                    whetherToShow(position = false, position1 = true)
                }
                R.id.episodeDetailFragment -> {
                    whetherToShow(position = false, position1 = true)
                }
                R.id.locationDetailFragment -> {
                    whetherToShow(position = false, position1 = true)
                }
                else -> {
                    whetherToShow(position = true, position1 = true)
                }
            }
        }

        with(binding.bottomNavigation) {
            setupWithNavController(navController)
            setOnItemReselectedListener {
                when (it.itemId) {
                    R.id.characterFragment,
                    R.id.episodeFragment,
                    R.id.locationFragment
                    -> {
                        bottomNavigationListener.onItemSelect()
                    }
                }
            }
        }
    }

    fun interface OnBottomNavigationSelected {
        fun onItemSelect()
    }

    fun setOnItemReselectedListener(bottomNavigationListener: OnBottomNavigationSelected) {
        this.bottomNavigationListener = bottomNavigationListener
    }

    private fun whetherToShow(position: Boolean, position1: Boolean) {
        binding.bottomNavigation.isVisible = position
        binding.insideToolbar.isVisible = position1
    }

    override fun onSupportNavigateUp(): Boolean {
        return (NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp())
    }
}
