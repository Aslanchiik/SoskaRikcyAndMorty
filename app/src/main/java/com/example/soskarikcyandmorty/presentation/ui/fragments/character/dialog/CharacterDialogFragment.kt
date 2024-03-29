package com.example.soskarikcyandmorty.presentation.ui.fragments.character.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.soskarikcyandmorty.databinding.FragmentCharacterDialogBinding
import com.example.soskarikcyandmorty.presentation.extensions.setDropMenu
import com.example.soskarikcyandmorty.presentation.extensions.setDropMenuForGender
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDialogFragment : DialogFragment() {

    lateinit var binding: FragmentCharacterDialogBinding

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentCharacterDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initialize()
        setupListener()
        return dialog

    }

    private fun initialize() = with(binding) {
        nameFilter.setOnClickListener {
            nameFilter.setAdapter(setDropMenu())
            nameFilter.showDropDown()
        }
        genderFilter.setOnClickListener {
            genderFilter.setAdapter(setDropMenuForGender())
            genderFilter.showDropDown()
        }
    }

    private fun setupListener() {
        binding.applyButton.setOnClickListener {
            val name = binding.nameFilter.text.toString()
            val gender = binding.genderFilter.text.toString()
            findNavController().navigate(
                CharacterDialogFragmentDirections.actionCharacterDialogFragmentToCharacterFragment(
                    name,
                    gender
                )
            )
        }
    }
}