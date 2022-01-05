package com.sokah.valorantapp.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.SkinsFragmentBinding
import com.sokah.valorantapp.view.adapters.AgentAdapter
import com.sokah.valorantapp.view.adapters.SkinAdapter
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

        binding.rvSkins.layoutManager= GridLayoutManager(context,2)

        val adapter = SkinAdapter()

        binding.rvSkins.adapter = adapter
        viewModel = ViewModelProvider(this).get(SkinsViewModel::class.java)


        viewModel.mutableSkinList.observe(this,{

            Log.e("TAG", it.data.size.toString() )
            adapter.SetSkins(it.data)
        })

        viewModel.isLoading.observe(this,{

            binding.progressBar3.isVisible=it
        })
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding=null
    }
}