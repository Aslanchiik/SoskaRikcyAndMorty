package com.example.soskarikcyandmorty.ui.fragments.character

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentCharacterBinding
import com.example.soskarikcyandmorty.domain.models.CharacterModel
import com.example.soskarikcyandmorty.presentation.state.UIState
import com.example.soskarikcyandmorty.ui.activity.MainActivity
import com.example.soskarikcyandmorty.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    private val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter: CharacterAdapter = CharacterAdapter()
    private var mLayoutManager: LinearLayoutManager = LinearLayoutManager(context)

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.characterRecView.apply {
            layoutManager = mLayoutManager
            adapter = characterAdapter
        }

        binding.characterRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = mLayoutManager.childCount
                    val totalItemCount = mLayoutManager.itemCount
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        viewModel.page++
                        viewModel.fetchCharacter(viewModel.page)
                    }
                }
            }
        })
    }

    override fun setupRequest() {
        bottomNavigationSelected()
        fetchCharacter()
        fetchGetCharacter()
    }

    private fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.characterRecView.smoothScrollToPosition(0)
        }
    }

    private fun fetchCharacter() {
        viewModel.fetchCharacter(1)
    }

    private fun fetchGetCharacter() {
        viewModel.characterState.observe(viewLifecycleOwner, {
            binding.loaderCharacter.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Timber.e("tag ${it.error}")
                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    val list = ArrayList<CharacterModel>(characterAdapter.currentList)
                    list.addAll(it.data)
                    characterAdapter.submitList(list)
                }
            }
        })
    }
}