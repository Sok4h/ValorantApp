package com.sokah.valorantapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.SkinsFragmentBinding
import com.sokah.valorantapp.viewmodel.SkinsViewModel

class SkinsFragment : Fragment(R.layout.skins_fragment) {

    private  lateinit var viewModel: SkinsViewModel
    private  var _binding :SkinsFragmentBinding?=null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SkinsFragmentBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(SkinsViewModel::class.java)

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding=null
    }
}