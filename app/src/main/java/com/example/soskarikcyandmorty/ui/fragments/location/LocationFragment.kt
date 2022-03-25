package com.example.soskarikcyandmorty.ui.fragments.location

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.bases.BaseFragment
import com.example.core.exensions.bindUIToLoadState
import com.example.core.utils.NetworkConnectionLiveData
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentLocationBinding
import com.example.soskarikcyandmorty.ui.activity.MainActivity
import com.example.soskarikcyandmorty.ui.adapters.LocationAdapter
import com.example.soskarikcyandmorty.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    private val viewModel: LocationViewModel by viewModels()
    private val locationAdapter: LocationAdapter = LocationAdapter(this::onItemClick)
    private val isConnect = true
    private val args by navArgs<LocationFragmentArgs>()

    override fun initialize() {
        setupConnection()
    }

    private fun setupConnection() {
        NetworkConnectionLiveData(context ?: return)
            .observe(viewLifecycleOwner) { isConnected ->
                if (!isConnected)
                    findNavController().navigate(R.id.noConnectFragment)
            }
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        locationRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { locationAdapter.retry() }
            )
        }
        locationAdapter.bindUIToLoadState(
            locationRecView,
            locationProgressBar
        ) {}
    }

    override fun setupRequest() {
        if (args.type == "" && args.dimension == "") {
            viewModel.fetchLocations("", "", "")
        } else {
            viewModel.fetchLocationsFilter("", args.type, args.dimension)
        }
    }

    override fun setupObserves() {
        if (isConnect) {
            if (args.type == "" && args.dimension == "") {
                viewModel.locationState.subscribePaging {
                    locationAdapter.submitData(it)
                }
            } else {
                viewModel.locationStateFilter.subscribePaging {
                    locationAdapter.submitData(it)
                }
            }
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(
                label = "${getString(R.string.fragment_detail_location)}$name",
                id = id
            )
        )
    }

    override fun setupListener() {
        searchItem()
        bottomNavigationSelected()
        filterListener()
    }

    private fun searchItem() {
//        if (args.type == "" && args.dimension == "") {
//            binding.searchLocation.searchItem {
//                viewModel.fetchLocations(
//                    it,
//                    "",
//                    ""
//                )
//            }
//        } else {
//            binding.searchLocation.searchItem {
//                viewModel.fetchLocationsFilter(
//                    it,
//                    args.type,
//                    args.dimension
//                )
//            }
//        }
    }

    private fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.locationRecView.smoothScrollToPosition(0)
        }
    }

    private fun filterListener() {
//        binding.filterFloatButton.setOnClickListener {
//            findNavController().navigate(R.id.action_locationFragment_to_locationDialogFragment)
//        }
    }
}