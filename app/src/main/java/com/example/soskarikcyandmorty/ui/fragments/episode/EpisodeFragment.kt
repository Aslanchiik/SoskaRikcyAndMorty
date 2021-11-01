package com.example.soskarikcyandmorty.ui.fragments.episode

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentEpisodeBinding
import com.example.soskarikcyandmorty.domain.models.EpisodeModel
import com.example.soskarikcyandmorty.presentation.state.UIState
import com.example.soskarikcyandmorty.ui.activity.MainActivity
import com.example.soskarikcyandmorty.ui.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    private val eLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private val episodeAdapter: EpisodeAdapter = EpisodeAdapter()
    private val viewModel: EpisodeViewModel by viewModels()

    override fun initialize() {
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        binding.episodeRecView.apply {
            layoutManager = eLayoutManager
            adapter = episodeAdapter
        }

        binding.episodeRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val firstVisibleItemPosition = eLayoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = eLayoutManager.childCount
                    val totalItemCount = eLayoutManager.itemCount
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        viewModel.page++
                        viewModel.fetchEpisode(viewModel.page)
                    }
                }
            }
        })
    }

    override fun setupRequest() {
        bottomNavigationSelected()
        fetchEpisode()
        fetchGetEpisode()
    }

    private fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.episodeRecView.smoothScrollToPosition(0)
        }
    }

    private fun fetchEpisode() {
        viewModel.fetchEpisode(1)
    }

    private fun fetchGetEpisode() {
        viewModel.episodeState.observe(viewLifecycleOwner, {
            binding.loaderEpisode.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Timber.e("tag ${it.error}")
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    val list = ArrayList<EpisodeModel>(episodeAdapter.currentList)
                    list.addAll(it.data)
                    episodeAdapter.submitList(list)
                }
            }
        })
    }
}