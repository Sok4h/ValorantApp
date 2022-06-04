package com.sokah.valorantapp.view.fragments

import CheckInternet
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentAgentsBinding
import com.sokah.valorantapp.view.adapters.AgentAdapter
import com.sokah.valorantapp.viewmodel.AgentListViewModel
import kotlinx.coroutines.launch


class AgentsFragment : Fragment(R.layout.fragment_agents) {

    private lateinit var viewmodel: AgentListViewModel
    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!
    val adapter = AgentAdapter()
    lateinit var internetConnection: CheckInternet

    lateinit var chips: Array<Chip>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)

         internetConnection = CheckInternet(requireContext())

        val layoutManager = GridLayoutManager(context, 2)
        binding.rvAgents.layoutManager = layoutManager
        chips = arrayOf(binding.chipDuelist, binding.chipController)
        binding.rvAgents.adapter = adapter

        viewmodel = ViewModelProvider(this)[AgentListViewModel::class.java]





        viewmodel.mutableAgentList.observe(viewLifecycleOwner) {

            adapter.setAgents(it!!)
            layoutManager.scrollToPositionWithOffset(0, 0)

        }

        viewmodel.isLoading.observe(viewLifecycleOwner) {

            binding.progressBar.isVisible = it
        }

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->


            val chip = binding.chipGroup.findViewById<Chip>(checkedId)

            if (chip != null) {

                lifecycleScope.launch{

                    viewmodel.filterAgent(chip.text.toString())

                }
            } else {
                viewmodel.getAgents()
            }


        }
        return binding.root


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

}