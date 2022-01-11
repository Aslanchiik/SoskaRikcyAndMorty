package com.example.soskarikcyandmorty.ui.fragments.episode.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.soskarikcyandmorty.databinding.FragmentEpisodeDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDialogFragment : DialogFragment() {

    lateinit var binding: FragmentEpisodeDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentEpisodeDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListener()
        return dialog

    }

    private fun setupListener() = with(binding) {
        applyButton.setOnClickListener {
            val name = nameEpisodeFilter.text.toString()
            val episode = episodeFilter.text.toString()
            findNavController().navigate(
                EpisodeDialogFragmentDirections.actionEpisodeDialogFragmentToEpisodeFragment(
                    name,
                    episode
                )
            )
        }
    }
}