package com.sokah.valorantapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sokah.valorantapp.databinding.FragmentAgentsBinding


class AgentsFragment : Fragment(R.layout.fragment_agents) {

    private var _binding : FragmentAgentsBinding?=null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentsBinding.inflate(inflater,container,false)
        return binding.root
    }


}