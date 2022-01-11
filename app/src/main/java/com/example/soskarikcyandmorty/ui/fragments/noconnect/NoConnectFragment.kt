package com.example.soskarikcyandmorty.ui.fragments.noconnect

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentNoConnectBinding
import com.example.soskarikcyandmorty.utils.NetworkConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoConnectFragment : BaseFragment<FragmentNoConnectBinding>(R.layout.fragment_no_connect) {

    override val binding by viewBinding(FragmentNoConnectBinding::bind)
    private var isCheckInternet = false

    override fun setupListener() {
        binding.tryAgainButton.setOnClickListener {
            if (isCheckInternet) {
                findNavController().navigateUp()
            }  else
                Toast.makeText(requireContext(), "Подключите интернет", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setupObserves() {
        NetworkConnectionLiveData(context ?: return)
            .observe(viewLifecycleOwner, { isConnected ->
                if (isConnected) isCheckInternet = isConnected
            })
    }
}
