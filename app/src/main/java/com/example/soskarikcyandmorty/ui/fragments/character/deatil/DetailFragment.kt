package com.example.soskarikcyandmorty.ui.fragments.character.deatil

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.soskarikcyandmorty.R
import com.example.soskarikcyandmorty.bases.BaseFragment
import com.example.soskarikcyandmorty.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    override val binding by viewBinding(FragmentDetailBinding::bind)

}