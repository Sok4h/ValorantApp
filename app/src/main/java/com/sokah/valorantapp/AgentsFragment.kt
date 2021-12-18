package com.sokah.valorantapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sokah.valorantapp.databinding.FragmentAgentsBinding
import com.sokah.valorantapp.viewmodel.AgentListViewModel


class AgentsFragment : Fragment(R.layout.fragment_agents) {

    private lateinit var viewmodel : AgentListViewModel
    private var _binding : FragmentAgentsBinding?=null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentsBinding.inflate(inflater,container,false)

        binding.rvAgents.layoutManager= LinearLayoutManager(context)
        val adapter = AgentAdapter()


        binding.rvAgents.adapter = adapter

        viewmodel = ViewModelProvider(this).get(AgentListViewModel::class.java)

        viewmodel.agentList.observe(this,{

            adapter.setAgents(it)
            Log.e("TAG", "onCreateView: ")

        })
        return binding.root


    }


}