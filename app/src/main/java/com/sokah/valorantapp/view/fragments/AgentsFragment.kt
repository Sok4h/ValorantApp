package com.sokah.valorantapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.sokah.valorantapp.view.AgentAdapter
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentAgentsBinding
import com.sokah.valorantapp.viewmodel.AgentListViewModel


class AgentsFragment : Fragment(R.layout.fragment_agents) {

    private lateinit var viewmodel : AgentListViewModel
    private var _binding : FragmentAgentsBinding?=null
    private val binding get()= _binding!!

    lateinit var  chips: Array<Chip>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentsBinding.inflate(inflater,container,false)


        binding.rvAgents.layoutManager= GridLayoutManager(context,2)

        chips = arrayOf(binding.chipDuelist,binding.chipController)
        val adapter = AgentAdapter()


        binding.rvAgents.adapter = adapter

        viewmodel = ViewModelProvider(this).get(AgentListViewModel::class.java)

        viewmodel.mutableAgentList.observe(this,{

            adapter.setAgents(it.data)


        })

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->


            Log.e("TAG", checkedId.toString())
            val chip = binding.chipGroup.findViewById<Chip>(checkedId)

            if (chip != null) {

                viewmodel.filterAgent(chip.text.toString())
            }else{
                viewmodel.getAgents()
            }


        }
            return binding.root


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null;
    }

}