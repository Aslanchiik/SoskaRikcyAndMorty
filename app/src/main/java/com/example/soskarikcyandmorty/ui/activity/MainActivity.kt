package com.example.soskarikcyandmorty.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationListener: OnBottomNavigationSelected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.characterFragment -> {
                    binding.toolbar.title = "Character"
                    binding.toolbar.setTitleTextColor(Color.WHITE)
                }
                R.id.locationFragment -> {
                    binding.toolbar.title = "Location"
                }
                else -> {
                    binding.toolbar.title = "Episode"
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
}
