package com.example.soskarikcyandmorty.presentation.ui.fragments.location.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.soskarikcyandmorty.databinding.FragmentLocationDilaogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDialogFragment : DialogFragment() {

    lateinit var binding: FragmentLocationDilaogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentLocationDilaogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupListener()
        return dialog

    }

    private fun setupListener() = with(binding) {
        applyButton.setOnClickListener {
            val type = typeFilter.text.toString()
            val dimension = dimensionFilter.text.toString()
            findNavController().navigate(
                LocationDialogFragmentDirections.actionLocationDialogFragmentToLocationFragment(
                    type,
                    dimension
                )
            )
        }
    }
}