package com.sokah.valorantapp.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.FragmentAgentsBinding
import com.sokah.valorantapp.ui.view.adapters.AgentAdapter
import com.sokah.valorantapp.ui.viewStates.AgentViewStates
import com.sokah.valorantapp.ui.viewmodel.AgentListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsFragment : Fragment(R.layout.fragment_agents) {

    private val viewmodel: AgentListViewModel by viewModels()
    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!
    val adapter = AgentAdapter()
    lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)

        setupRV()
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            filterAgents(checkedId)
        }
        viewmodel.getAgents()

        viewmodel.viewState.observe(viewLifecycleOwner) { agentViewState ->

            when (agentViewState) {

                is AgentViewStates.AgentListSuccess -> {
                    adapter.setAgents(agentViewState.data)
                    layoutManager.scrollToPositionWithOffset(0, 0)
                    binding.progressBar.isVisible = false
                }

                is AgentViewStates.Loading -> binding.progressBar.isVisible = true

                is AgentViewStates.Error -> {

                    Toast.makeText(context, agentViewState.error.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                    //showSnackBar()
                }

                else -> {}
            }
        }
        return binding.root
    }


    private fun showSnackBar() {

        val bottomNavView: BottomNavigationView =
            activity?.findViewById(R.id.bottomNavigationView)!!

        Snackbar.make(binding.root, R.string.no_internet, LENGTH_INDEFINITE)
            .setAnimationMode(ANIMATION_MODE_FADE)
            .setAction("Retry") {

                viewmodel.getAgents()
            }.setAnchorView(bottomNavView)

            .show()
    }

    fun setupRV() {

        layoutManager = GridLayoutManager(context, 2)
        binding.rvAgents.layoutManager = layoutManager
        binding.rvAgents.adapter = adapter
    }

    fun filterAgents(checkedId: Int) {

        val chip = binding.chipGroup.findViewById<Chip>(checkedId)

        if (chip != null) {
            viewmodel.filterAgent(chip.text.toString())
        } else {
            viewmodel.getAgents()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }

}