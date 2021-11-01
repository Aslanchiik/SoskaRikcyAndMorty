package com.example.soskarikcyandmorty.ui.fragments.location

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentLocationBinding
import com.example.soskarikcyandmorty.domain.models.LocationModel
import com.example.soskarikcyandmorty.presentation.state.UIState
import com.example.soskarikcyandmorty.ui.activity.MainActivity
import com.example.soskarikcyandmorty.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    private val viewModel: LocationViewModel by viewModels()
    private val lLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private val locationAdapter: LocationAdapter = LocationAdapter()

    override fun initialize() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.locationRecView.apply {
            layoutManager = lLayoutManager
            adapter = locationAdapter
        }
        binding.locationRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val firstVisibleItemPosition = lLayoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = lLayoutManager.childCount
                    val totalItemCount = lLayoutManager.itemCount
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        viewModel.page++
                        viewModel.fetchLocation(viewModel.page)
                    }
                }
            }
        })
    }

    override fun setupRequest() {
        bottomNavigationSelected()
        fetchLocation()
        fetchGetLocation()
    }

    private fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.locationRecView.smoothScrollToPosition(0)
        }
    }

    private fun fetchLocation() {
        viewModel.fetchLocation(1)
    }

    private fun fetchGetLocation() {
        viewModel.locationState.observe(viewLifecycleOwner, {
            binding.loaderLocation.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Error -> {

                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    val list = ArrayList<LocationModel>(locationAdapter.currentList)
                    list.addAll(it.data)
                    locationAdapter.submitList(list)
                }
            }
        })
    }
}