package com.example.soskarikcyandmorty.presentation.ui.fragments.character.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.soskarikcyandmorty.databinding.FragmentDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DialogFragment : DialogFragment() {

    lateinit var binding: FragmentDialogFragmentBinding

    private val args: DialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogFragmentBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(activity).setView(binding.root).create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initialize()
        return dialog
    }

    private fun initialize() {
        getDataImage()
    }

    private fun getDataImage() {
        Glide.with(binding.imageDialog).load(args.image).into(binding.imageDialog)
    }
}