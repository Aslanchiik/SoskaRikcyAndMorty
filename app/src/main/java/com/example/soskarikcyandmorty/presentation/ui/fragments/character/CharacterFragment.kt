package com.example.soskarikcyandmorty.presentation.ui.fragments.character

import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.Resource
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.databinding.FragmentCharacterBinding
import com.example.soskarikcyandmorty.presentation.base.BaseFragment
import com.example.soskarikcyandmorty.presentation.extensions.bindUIToLoadState
import com.example.soskarikcyandmorty.presentation.extensions.navigateSafely
import com.example.soskarikcyandmorty.presentation.ui.activity.MainActivity
import com.example.soskarikcyandmorty.presentation.ui.adapters.CharacterAdapter
import com.example.soskarikcyandmorty.presentation.ui.adapters.paging.LoadStateAdapter
import com.example.soskarikcyandmorty.utils.NetworkConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    private val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(
        this::onItemClick,
        this::fetchFirstSeenIn,
        this::onItemLongClick
    )
    private var isConnected = true
    private val args by navArgs<CharacterFragmentArgs>()

    override fun initialize() {
        setupConnection()
    }

    private fun setupConnection() {
        NetworkConnectionLiveData(context ?: return)
            .observe(viewLifecycleOwner) { isConnected ->
                if (!isConnected)
                    findNavController().navigate(R.id.action_characterFragment_to_noConnectFragment)
            }
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        characterRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { characterAdapter.retry() }
            )
        }
        characterAdapter.bindUIToLoadState(
            characterRecView,
            characterProgressBar
        ) {}
    }

    override fun setupRequest() {
        if (args.status == "" || args.gender == "") {
            if (viewModel.characterState.value == null) {
                viewModel.fetchCharacters("", "", "")
            }
        } else {
            viewModel.fetchCharactersFilter("", args.status, args.gender)
        }
    }

    override fun setupObserves() {
        if (isConnected) {
            if (args.status == "" || args.gender == "") {
                viewModel.characterState.collectPaging {
                    characterAdapter.submitData(it)
                }
            }
        } else {
            viewModel.characterStateFilter.collectPaging {
                characterAdapter.submitData(it)
            }
        }
    }

    override fun setupListener() {
        searchItems()
        bottomNavigationSelected()
//        filterListener()
    }

    private fun searchItems() {
//        if (args.status == "" || args.gender == "") {
//            binding.searchCharacter.searchItem {
//                if (viewModel.charactersState.value == null) {
//                    viewModel.fetchCharacters(
//                        it,
//                        "",
//                        ""
//                    )
//                }
//            }
//        } else {
//            binding.searchCharacter.searchItem {
//                viewModel.fetchCharactersFilter(
//                    it,
//                    args.status,
//                    args.gender
//                )
//            }
//        }
    }

    private fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.characterRecView.smoothScrollToPosition(0)
        }
    }

//    private fun filterListener() {
//        binding.filterFloatButton.setOnClickListener {
//            findNavController().navigate(R.id.action_characterFragment_to_characterDialogFragment)
//        }
//    }

    private fun fetchFirstSeenIn(position: Int, episodeUrl: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchEpisode(episodeUrl.getIdFromUrl()).collect {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data?.let { it1 -> characterAdapter.setFirstSeenIn(position, it1.name) }
                    }
                }
            }
        }
    }

    private fun String.getIdFromUrl() = Uri.parse(this).lastPathSegment?.toInt()!!

    private fun onItemClick(name: String, id: Int) {
        findNavController().navigateSafely(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                label = "${getString(R.string.fragment_detail_character)}$name",
                id = id,
            )
        )
    }

    private fun onItemLongClick(image: String) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToDialogFragment(image)
        )
    }
}