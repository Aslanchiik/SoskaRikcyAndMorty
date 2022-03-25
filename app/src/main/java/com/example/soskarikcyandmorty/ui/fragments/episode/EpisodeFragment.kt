package com.example.soskarikcyandmorty.ui.fragments.episode

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.bases.BaseFragment
import com.example.core.exensions.bindUIToLoadState
import com.example.core.utils.NetworkConnectionLiveData
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentEpisodeBinding
import com.example.soskarikcyandmorty.ui.activity.MainActivity
import com.example.soskarikcyandmorty.ui.adapters.EpisodeAdapter
import com.example.soskarikcyandmorty.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    private val episodeAdapter: EpisodeAdapter = EpisodeAdapter(this::onItemClick)
    private val viewModel: EpisodeViewModel by viewModels()
    private var isConnected = true
    private val args by navArgs<EpisodeFragmentArgs>()

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
        setupRecyclerview()
    }

    private fun setupRecyclerview() = with(binding) {
        episodeRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { episodeAdapter.retry() }
            )
        }
        episodeAdapter.bindUIToLoadState(
            episodeRecView, episodeProgressBar
        ) {}
    }

    override fun setupRequest() {
        if (args.name == "" && args.episode == "") {
            viewModel.fetchEpisodes("", "")
        } else {
            viewModel.fetchEpisodesFilter(args.name, args.episode)
        }
    }

    override fun setupObserves() {
        if (isConnected) {
            if (args.name == "" && args.episode == "") {
                viewModel.episodeState.subscribePaging {
                    episodeAdapter.submitData(it)
                }
            } else {
                viewModel.episodeStateFilter.subscribePaging {
                    episodeAdapter.submitData(it)
                }
            }
        }
    }

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(
                label = "${getString(R.string.fragment_detail_episode)}$name",
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
//        if (args.name == "" || args.episode == "") {
//            binding.searchEpisode.searchItem {
//                viewModel.fetchEpisodes(
//                    it,
//                    ""
//                )
//            }
//        } else {
//            binding.searchEpisode.searchItem {
//                viewModel.fetchEpisodesFilter(
//                    it,
//                    args.episode
//                )
//            }
//        }
    }

    private fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.episodeRecView.smoothScrollToPosition(0)
        }
    }

    private fun filterListener() {
//        binding.filterFloatButton.setOnClickListener {
//            findNavController().navigate(R.id.action_episodeFragment_to_episodeDialogFragment)
//        }
    }
}